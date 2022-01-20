import java.io.*;
import java.util.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.stream.*;

public class CS_Server extends JFrame implements ActionListener {
   // awt,event ����
   JPanel contentPane, panel_Main, panel_TextArea, panel_Btn;
   JScrollPane scrollPane;
   JTextArea textArea;
   JLabel label_ServerStatus;
   JButton btn_ServerStart, btn_ServerClose;

   // ���� ����
   ServerSocket ss;
   Socket s;
   int port = 7777;

   // Ŭ���̾�Ʈ ����
   public static final int MAX_CLIENT = 4; // Ŭ���̾�Ʈ �� ����
   int readyPlayer; // ���� �غ�� Ŭ���̾�Ʈ ī��Ʈ
   int score;
   int iScore;
   String clientName;
   Vector<String> vtScore = new Vector<String>(); // ���� + Ŭ���̾�Ʈ ��
   ArrayList<Integer> clientScore = new ArrayList<Integer>(); // ����
   LinkedHashMap<String, DataOutputStream> clientList = new LinkedHashMap<String, DataOutputStream>(); // Ŭ���̾�Ʈ �̸�, ��Ʈ��
                                                                                                       // ����
   LinkedHashMap<String, Integer> clientInfo = new LinkedHashMap<String, Integer>(); // Ŭ���̾�Ʈ �̸�, ���� ����

   boolean gameStart; // ���� ���� ����
   String line = "";

   public void init() {
      setTitle("JAVA");
      setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 450, 450);
      setLocationRelativeTo(null);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(new GridLayout(0, 1, 10, 0));

      panel_Main = new JPanel();
      contentPane.add(panel_Main);
      panel_Main.setLayout(new BoxLayout(panel_Main, BoxLayout.Y_AXIS));

      label_ServerStatus = new JLabel(new ImageIcon("image\\Logo3.png"));
      label_ServerStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
      label_ServerStatus.setPreferredSize(new Dimension(96, 50));
      panel_Main.add(label_ServerStatus);
      label_ServerStatus.setHorizontalTextPosition(SwingConstants.CENTER);
      label_ServerStatus.setHorizontalAlignment(SwingConstants.CENTER);
      label_ServerStatus.setFont(new Font("�����ٸ����", Font.PLAIN, 20));
      label_ServerStatus.setBackground(Color.GRAY);

      panel_TextArea = new JPanel();
      panel_Main.add(panel_TextArea);
      panel_TextArea.setLayout(new BorderLayout(0, 0));
      panel_TextArea.setBounds(100, 100, 100, 100);

      scrollPane = new JScrollPane();
      scrollPane.setBorder(new LineBorder(Color.ORANGE));
      panel_TextArea.add(scrollPane);

      textArea = new JTextArea();
      textArea.setLineWrap(true);
      textArea.setEditable(false);
      scrollPane.setViewportView(textArea);

      panel_Btn = new JPanel();
      panel_Btn.setPreferredSize(new Dimension(10, 43));
      panel_Btn.setAutoscrolls(true);
      panel_Main.add(panel_Btn);
      panel_Btn.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

      btn_ServerStart = new JButton(" ���� ���� ");
      btn_ServerStart.setHorizontalTextPosition(SwingConstants.CENTER);
      btn_ServerStart.setPreferredSize(new Dimension(120, 40));
      btn_ServerStart.setFocusPainted(false);
      btn_ServerStart.setFont(new Font("�����ٸ����", Font.BOLD, 16));
      btn_ServerStart.setAlignmentX(Component.CENTER_ALIGNMENT);
      btn_ServerStart.setForeground(Color.WHITE);
      btn_ServerStart.setBackground(Color.ORANGE);
      btn_ServerStart.setBorder(null);
      panel_Btn.add(btn_ServerStart);
      btn_ServerStart.addActionListener(this);

      btn_ServerClose = new JButton(" ���� ���� ");
      btn_ServerClose.setHorizontalTextPosition(SwingConstants.CENTER);
      btn_ServerClose.setPreferredSize(new Dimension(120, 40));
      btn_ServerClose.setFocusPainted(false);
      btn_ServerClose.setFont(new Font("�����ٸ����", Font.BOLD, 16));
      btn_ServerClose.setAlignmentX(Component.CENTER_ALIGNMENT);
      btn_ServerClose.setForeground(Color.WHITE);
      btn_ServerClose.setBackground(Color.ORANGE);
      btn_ServerClose.setBorder(null);
      panel_Btn.add(btn_ServerClose);
      btn_ServerClose.addActionListener(this);
      btn_ServerClose.setEnabled(false);
   }

   public void actionPerformed(ActionEvent e) { // '���� ���� & ����' ��ư �̺�Ʈ
      if (e.getSource() == btn_ServerStart) {
         new Thread() {
            public void run() {
               try {
                  Collections.synchronizedMap(clientList);
                  ss = new ServerSocket(port);
                  textArea.append("[ ������ ���۵Ǿ����ϴ� ]" + "\n");
                  btn_ServerStart.setEnabled(false);
                  btn_ServerClose.setEnabled(true);
                  while (true) {
                     s = ss.accept(); // ���� ����
                     if ((clientList.size() + 1) > MAX_CLIENT || gameStart == true) { // ������ �ʰ��Ǿ��ų�, �������̶�� ���� ���� �ź�
                        s.close();
                     } else {
                        Thread gm = new GameManager(s);
                        gm.start();
                     }
                  }
               } catch (IOException io) {
               }
            }
         }.start();
      } else if (e.getSource() == btn_ServerClose) {
         int select = JOptionPane.showConfirmDialog(null, "������ ���� �����Ͻðڽ��ϱ�?", "JAVA ", JOptionPane.OK_CANCEL_OPTION);
         try {
            if (select == JOptionPane.YES_OPTION) {
               ss.close();
               textArea.append("[ ������ ����Ǿ����ϴ� ]" + "\n");
               btn_ServerStart.setEnabled(true);
               btn_ServerClose.setEnabled(false);
            }
         } catch (IOException io) {
         }
      }
   }

   public void showSystemMsg(String msg) { // �ؽ�Ʈâ�� ������ �ϴ� �޼ҵ�.
      Iterator<String> it = clientList.keySet().iterator();
      while (it.hasNext()) {
         try {
            DataOutputStream dos = clientList.get(it.next());
            dos.writeUTF(msg);
            dos.flush();
         } catch (IOException io) {
         }
      }
   }

   public class GameManager extends Thread {
      Socket s;
      DataInputStream dis;
      DataOutputStream dos;

      public GameManager(Socket s) {
         this.s = s;
         try {
            dis = new DataInputStream(this.s.getInputStream());
            dos = new DataOutputStream(this.s.getOutputStream());
         } catch (IOException io) {
         }
      }

      public void run() {
         String clientName = "";
         try {
            clientName = dis.readUTF();
            if (!clientList.containsKey(clientName)) { // �ߺ� �г��� ����
               clientList.put(clientName, dos);
               clientInfo.put(clientName, score);
            } else if (clientList.containsKey(clientName)) {
               s.close();
            }
            showSystemMsg("[ " + clientName + "���� �����ϼ̽��ϴ�. ]\n(���� ������ �� : " + clientList.size() + "�� / 4��)");
            textArea.append("[ ���� ������ ��� (�� " + clientList.size() + "�� ������) ]\n");
            showSystemMsg("�¸����� : ������ ���� ���� ����� �¸��մϴ�.\n");
            Iterator<String> it1 = clientList.keySet().iterator();
            while (it1.hasNext())
               textArea.append(it1.next() + "\n");
            scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
            setClientInfo();
            while (dis != null) {
               String msg = dis.readUTF();
               filter(msg); // ��ɾ� ���͸�
            }
         } catch (IOException io) {
            clientList.remove(clientName);
            clientInfo.remove(clientName); // Ŭ���̾�Ʈ ����� ����
            closeAll();
            if (clientList.isEmpty() == true) { // ������ ���� Ŭ���̾�Ʈ�� �ϳ��� ���ٸ�, ���� �ݱ�
               try {
                  ss.close();
                  System.exit(0);
               } catch (IOException e) {
               }
            }
            showSystemMsg("[ " + clientName + "���� �����ϼ̽��ϴ�. ]\n(���� �����ڼ� : " + clientList.size() + "�� / 4��)");
            textArea.append("[ ���� ������ ��� (�� " + clientList.size() + "�� ������) ]\n");
            Iterator<String> it1 = clientList.keySet().iterator();
            while (it1.hasNext())
               textArea.append(it1.next() + "\n");
            scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
            setClientInfo(); // Ŭ���̾�Ʈ ��� ����
            readyPlayer = 0; // ���ο� Ŭ���̾�Ʈ�� �����ص� ���� ���ۿ� ������ ������ ���� �ʱ�ȭ
            gameStart = false;
            showSystemMsg("//GmEnd"); // Ŭ���̾�Ʈ �����, ��� ���� ����
         }
      }

      public void closeAll() {
         try {
            if (dos != null)
               dos.close();
            if (dis != null)
               dis.close();
            if (s != null)
               s.close();
         } catch (IOException ie) {
         }
      }

      public void setClientInfo() {
         String[] keys = new String[clientInfo.size()];
         int[] values = new int[clientInfo.size()];
         int index = 0;
         for (Map.Entry<String, Integer> mapEntry : clientInfo.entrySet()) {
            keys[index] = mapEntry.getKey();
            values[index] = mapEntry.getValue();
            index++;
         }
         for (int i = 0; i < clientList.size(); i++) {
            showSystemMsg("//CList" + keys[i] + " " + values[i] + "#" + i); // ��ɾ� : Ŭ���̾�Ʈ ��� ����
         }
      }

      public void filter(String msg) { // ��ɾ� ���͸�
         String temp = msg.substring(0, 7);

         if (temp.equals("//Chat ")) { // ��ɾ� : �Ϲ� ä��
            showSystemMsg(msg.substring(7));
         } else if (temp.equals("//Ready")) { // ��ɾ� : Ŭ���̾�Ʈ �غ� ���� üũ
            readyPlayer++;
            if (readyPlayer >= 2 && readyPlayer == clientList.size()) { // 2�� �̻� && ��� Ŭ���̾�Ʈ�� �غ�Ǿ��� ���
               for (int i = 3; i > 0; i--) {
                  try {
                     showSystemMsg("[ ��� �����ڵ��� �غ�Ǿ����ϴ�. ]\n[ " + i + "�� �� ������ �����մϴ� .. ]");
                     Thread.sleep(1000);
                  } catch (InterruptedException ie) {
                  }
               }
               showSystemMsg("//Start"); // ��ɾ� : ���� ����
               clientScore.clear();
               vtScore.clear();
               StopWatch tm = new StopWatch();
               tm.start(); // Ÿ�̸� ����
               gameStart = true;
            }
         } else if (temp.equals("//Mouse")) { // ��ɾ� : ���콺 ��ǥ ����
            showSystemMsg(msg);
         } else if (temp.equals("//Color")) { // ��ɾ� : �÷� ����
            showSystemMsg(msg);
         } else if (temp.equals("//Erase")) { // ��ɾ� : �����
            showSystemMsg(msg);
         } else if (temp.equals("//ErAll")) { // ��ɾ� : ��� �����
            showSystemMsg(msg);
         } else if (temp.equals("//score")) {
            String scoreC = msg.substring(7, msg.indexOf(" "));
            String scoreM = msg.substring(9, msg.length());
            iScore = Integer.parseInt(scoreC);
            clientScore.add(iScore);
            Collections.sort(clientScore, Collections.reverseOrder());

            String score2 = msg.substring(7, msg.length()); // ���� �� ���̵�
            String score3 = score2.substring(0, score2.indexOf(" "));
            vtScore.add(score2);
            showSystemMsg("< " + scoreM + " > ���� ������ ( " + iScore + " ) �Դϴ�. ");
         } else if (temp.equals("//GmGG ")) { // ��ɾ� : ���� ���� (�����ڰ� ������ �������� ���)
            showSystemMsg("[ �����ڰ� ������ �����߽��ϴ� !! ]");
            showSystemMsg(msg);
            readyPlayer = 0;
            gameStart = false;
         } else if (temp.equals("//SGEnd")) { // ��ɾ� : ���� ���� (�ð� �ʰ��� ��Ż�� �߻����� ������ ����Ǵ� ���)
            System.out.println("���� �����-����");
            showSystemMsg("[ ������ ����Ǿ����ϴ� !! ]");
            showSystemMsg(msg);
            readyPlayer = 0;
            gameStart = false;
         }
      }
   }

   class StopWatch extends Thread {
      long preTime = System.currentTimeMillis();

      public void run() {
         try {
            while (gameStart == true) {
               sleep(10);
               long time = System.currentTimeMillis() - preTime;
               showSystemMsg("//Timer" + (toTime(time)));
               if (toTime(time).equals("00 : 00")) {
                  showSystemMsg("//GmEnd"); // �ð� �ʰ���, ���� ����
                  readyPlayer = 0;
                  gameStart = false;
                  break;
               } else if (readyPlayer == 0) {
                  break;
               }
            }
         } catch (Exception e) {
         }
      }

      String toTime(long time) { // ���� �ð�����
         int m = (int) (0 - (time / 1000.0 / 60.0));
         int s = (int) (30 - (time % (1000.0 * 60) / 1000.0));
         return String.format("%02d : %02d", m, s);
      }
   }

   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               CS_Server cms = new CS_Server();
               cms.init();
               cms.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }
}
