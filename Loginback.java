import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.ResultSet;
import java.sql.DatabaseMetaData;
import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.Date;
import java.util.Vector;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.GradientPaint;
import java.text. *;
import java.util.Random;
import java.time.*;
//package com.ict.jdbc_hr;
//set classpath=.;C:\Users\qaz77\Desktop\fd\ojdbc8.jar
//뽑아낼수있는값 , 유저가가지고있는 테이블,기본키,기본키의인덱스,컬럼명,컬럼수,로우수,pk이름,테이블내용
// JOptionPane.showMessageDialog(null, "입력하신 데이터 ", "기본 키 또는 값 불명", JOptionPane.OK_OPTION);
//문제 1 , 업데이트 후 셀렉테이블하면 인덱스아웃 오류  // 해결
//문제 2 , 인설트 하려면 텍스트필드 남아있어야함.  //textArray[k33].setEnabled(true); 인설트할땐 쓸수 있게만들기.// 해결
//문제 3 , select 문 넣을 공간에 테이블관련도 넣기 // 완료 = 13번 해결방향결정.
//문제 4 , 익셉션 뜨면 경고창 띄워서 못하게하기 // 완료 
//문제 5 , insert get 로딩 문제 // 해결
//문제 6 , 종료하는 시스템 버튼 만들고 // 해결
//문제 7 , 글자색바꾸고 // 해결
//문제 8 , 셀렉문에 조인문 , 그룹바이, 오더바이 가능할까요 말까요 // 해결
//문제 9 , insertsetting 하는거 눌러야 insert 활성화, 기본키 활성화 // 해결
//문제 10, 기본키인 PK가 가변적으로 관례상 첫번째만 들어가는게 아니라 마지막에도 들어 갈 수 있어서 PK인덱스값을 뽑아서 그 컬럼에 맞는 곳을 비활성화 // 해결
//문제 11, 인설트 눌러서 PK 중복오류가 발생하면 느려지는데 , 그걸 해결하기위해서 InsertSetting 버튼을 눌러야 활성화 되게 , 인설트 후에는 다시 비활성화 //해결
//문제 12, 키가 없는 테이블에 인설트 누르면 널 값의 로우가 생긴당. // 해결
//문제 13, 검색할 내용 텍스트필드에 포커스가 되고나면 콤보박스를 Enable(true) 로 하고 , 그 뒤 listListener 써서 실행 메소드를 불러오기로하자. // 해결
//문제 14, 크리에이트문 같은 DDL 을 콤보박스로 선택했을 때 이벤트발생으로 기본값을 주게 하자. 버튼을 원하는 방향으로 바꿀수 있나.. // 해결
//문제 15,  데이트 파일 땡겨올때 잘라서가져와야함. // 해결
//문제 16, 검색창 백스페이스 , 크리에이트 테이블하면 안보임 // 해결
//문제 18, 인출시퀀스문제 DATE 파일 넣을때 sysdate 인식문제인거같은데..  // 해결 -> jtable 로 바로 넣으려고 해서 오류나는거 넣었다가 다시 가져오면됨
//문제 19, 테이블 변경시 add Combobox 실시간 변경 // 경우의수 1 , " " split 써서 배열로 이름 땡겨오기 , 경우의수 2 땡겨왔을때 ( 붙여쓰는 사람들을 위해 substring 할 것. //해결
//문제 20, 테이블 변경시 자동 커밋 문제가 있으니까 , yes 일때 만들고 no 일 때 리턴 시킬 것, // 해결
		//문제 21, commit 부분 만들 것, // 커밋버튼을 만들까요
//문제 22, id 로그인 하는 부분 만들 것 //해결
//문제 23, 기본 field 에 sysdate 가능하게 할 것//  해결
//문제 24, 셀렉해서 - 뷰생성 - 뷰땡겨서 거기서 검색 // 완료
//문제 25, 망했따 딜리트했는데 검색됨;; 버튼마다 if로 텍스트필드 읽어서 null 이면 셀렉 안되게 하자. // 해결
//문제 26, 아래 텍스트에서 검색하면 기본키열림 // 해결
//문제 27, 뷰 생성할 때 입력창 띄워서 뷰이름 생성하게 하고 , 하고난뒤에 그거 받아와서 삭제  ,  기본키 위치가 이상함. 기본키 없는애 검색하면, //완료
//문제 28, 정렬의 문제 기본 테이블 정렬 // 완료
//문제 29, SYSDATE 와 DATE 의 값을 '' 구분자에 구분을 줘야함 // 해결
//문제 30, 뷰생성해서도 내림차순과 오름차순을 줄 수 있어야함 // 해결
//문제 31, 뷰생성하고 검색하면서도 내림차순과 오름차순 정렬을 사용 할 수 있어야함 // 해결
		//문제 32, 뷰생성해서 오름차순 내림차순을 하려면 데이트를 찾아서 거기도 구분자를 줘야하는데 테이블네임으로 못읽어와서 하나씩 따서 와야함.
//문제 33, select 체크박스 사용 후에 기본검색으로 이동하게끔해서 에러발생 빈도를 줄이자. // 해결
//문제 34, 뷰자동삭제 // 해결
//문제 35, 정렬 검색후 새로 검색하면 기본정렬로 보내기 //해결
//문제 36, t테이블 박스를 만지면 기본검색으로 이동 시키자.
public class Loginback  extends JFrame implements ActionListener 
{   
	JPanel panel_TOP, panel_CENTER, panel_BOTTOM, panel_CENTER_TOP, panel_CENTER_BOTTOM , panel_CENTER_CENTER;
	JLabel label_Id, label_Url, label_Icon, label_Main, label_Pass;
	JTextField tf_Id, tf_Url;
	JButton btn_Connect, btn_Exit;
	Connection con;
	Container cp;
	JPasswordField tf_Pass;
	boolean conBoolean;
	public static String url , id , pass;

	Loginback(){
	    init();	
		loginDefaultSetting();
	}
	public void init() {
		cp = getContentPane();
		panel_TOP = new JPanel();
		panel_CENTER = new JPanel();
		panel_BOTTOM = new JPanel();
		panel_CENTER_TOP = new JPanel();
		panel_CENTER_BOTTOM = new JPanel();
		panel_CENTER_CENTER = new JPanel();
		
		label_Id = new JLabel("ID");
		label_Id.setFont(new Font("나눔바른고딕", Font.BOLD, 17));
		label_Id.setHorizontalAlignment(SwingConstants.CENTER);
		label_Url = new JLabel("URI");
		label_Url.setFont(new Font("나눔바른고딕", Font.BOLD, 17));
		label_Url.setHorizontalAlignment(SwingConstants.CENTER);
		label_Main = new JLabel("SQL");
		label_Main.setFont(new Font("나눔바른고딕", Font.BOLD, 30));
		label_Main.setHorizontalAlignment(SwingConstants.CENTER);
		label_Pass = new JLabel("PASSWORD");
		label_Pass.setFont(new Font("나눔바른고딕", Font.BOLD, 17));
		label_Pass.setHorizontalAlignment(SwingConstants.CENTER);
		tf_Url = new JTextField(24);
		tf_Id = new JTextField(25);
		tf_Pass = new JPasswordField(18);
		btn_Connect = new JButton("접속");
		btn_Exit = new JButton("종료");

		panel_TOP.setBackground(new Color(255, 255, 255));
		panel_CENTER.setBackground(new Color(255, 255, 255));
		panel_BOTTOM.setBackground(new Color(255, 255, 255));

		panel_TOP.setLayout(new BorderLayout());	
		panel_CENTER.setLayout(new FlowLayout());
		panel_BOTTOM.setLayout(new FlowLayout());

		tf_Url.setBorder(new LineBorder(Color.ORANGE, 1));
		tf_Url.setForeground(Color.BLACK);
		tf_Url.setBackground(new Color(255, 255, 255));
		tf_Id.setBorder(new LineBorder(Color.ORANGE, 1));
		tf_Id.setForeground(Color.BLACK);
		tf_Id.setBackground(new Color(255, 255, 255));
		tf_Pass.setBorder(new LineBorder(Color.ORANGE, 1));
		tf_Pass.setForeground(Color.BLACK);
		tf_Pass.setBackground(new Color(255, 255, 255));
		
		panel_TOP.add(label_Main);

		panel_CENTER_TOP.add(label_Url); 
		panel_CENTER_TOP.add(tf_Url);
		panel_CENTER_BOTTOM.add(label_Id);
		panel_CENTER_BOTTOM.add(tf_Id);
		panel_CENTER_CENTER.add(label_Pass);
		panel_CENTER_CENTER.add(tf_Pass);
		panel_CENTER.add(panel_CENTER_TOP);
		panel_CENTER.add(panel_CENTER_BOTTOM);
		panel_CENTER.add(panel_CENTER_CENTER);
		
		btn_Connect.setBorderPainted(false);
		btn_Exit.setBorderPainted(false);
		btn_Connect.setFocusPainted(false);
		btn_Exit.setFocusPainted(false);
		btn_Connect.setBackground(new Color(0, 0, 0));
		btn_Exit.setBackground(new Color(0, 0, 0));
		btn_Connect.setForeground(Color.WHITE);
		btn_Exit.setForeground(Color.WHITE);

		panel_BOTTOM.add(btn_Connect);
		panel_BOTTOM.add(btn_Exit);
		btn_Connect.addActionListener(this);
		btn_Exit.addActionListener(this);

		cp.add(panel_TOP, BorderLayout.NORTH);
		cp.add(panel_CENTER, BorderLayout.CENTER);
		cp.add(panel_BOTTOM, BorderLayout.SOUTH);

		setVisible(true);
		setTitle("Login");
		setSize(500, 250);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource() == btn_Connect){
			if (tf_Url.getText().equals("")){
				JOptionPane.showMessageDialog(null, "url을 입력해주세요.", "ERROR!", JOptionPane.WARNING_MESSAGE);
			}else if(tf_Id.getText().equals("")){
				JOptionPane.showMessageDialog(null, "SQL 사용자명을 입력해주세요.", "ERROR!", JOptionPane.WARNING_MESSAGE);
			}else if(tf_Pass.getText().equals("")){
				JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요", "ERROR!", JOptionPane.WARNING_MESSAGE);
			}else{
				url = tf_Url.getText().trim();
				id = tf_Id.getText().trim();
				pass = tf_Pass.getText().trim();
				DBcheck();
				btn_Connect.setEnabled(false);
				tf_Id.setEnabled(false);
				tf_Url.setEnabled(false);
				tf_Pass.setEnabled(false);
				setVisible(false);
				if(conBoolean){
					JAE1 jae = new JAE1(url ,id ,pass);
				}
			}
		}else if(e.getSource() == btn_Exit){
			int Loginexits = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?", "종료", JOptionPane.YES_NO_OPTION);
			 if(Loginexits == JOptionPane.YES_OPTION){  
				System.exit(0);
			 }else if(Loginexits == JOptionPane.NO_OPTION){
				return;
			 }
		}
	}

	boolean DBcheck(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url , id , pass); // 기본 값 scott , tiger
			conBoolean = true;
		}catch(ClassNotFoundException ce){
			pln("ClassNotFoundException");
			JOptionPane.showMessageDialog(null, "드라이버 확인 바랍니다.", "ERROR!", JOptionPane.WARNING_MESSAGE);
			conBoolean = false;
		}catch(SQLException se){
			pln("SQLException");
			JOptionPane.showMessageDialog(null, "DB 연결 실패 계정정보를 확인해주세요.", "ERROR!", JOptionPane.WARNING_MESSAGE);
			conBoolean = false;
			new Login();
		}
		return conBoolean; 	
	}

	void loginDefaultSetting(){
		tf_Url.setText("jdbc:oracle:thin:@127.0.0.1:1521:JAVA");
		tf_Id.setText("scott");
		tf_Pass.setText("tiger");
	}

	void p(String str){
		System.out.print(str);
	}

	void pln(String str){
		System.out.println(str);
	}

	public static void main(String[] args) 
	{	
		Loginback l = new Loginback();
	}
}


class JAE1 extends JFrame {
	
	Container cp;
	JTable jtable1;
	JComboBox tCombo, t1Combo;
	JTextField textField1 , textField2 ,textField3 ;
	JPanel jp1, jp2 , jp3 , panel_Btn , jp4 , jp5 ,jp6 ,bp1,bp2 ,checkpa , checkpa2 , checkpa3 , checkpa4 , checkpa5;
	JButton Binsert , Bupdate , Bdelete ;
	JLabel tablenameLabel , columnnameLabel , textField2Label , selectLabel , insertLabel , DdlSelectLabel , checkLa1 , checkLa2;
	JButton selectExecute ,  orderByB ,fieldB , insertB , updateB , deleteB , dropB , selectB , createB ,BColumnFieldUpdate , setInsert , exitB , viewB  , viewBColse ; 
	JCheckBox ASCchks, ASCchk, DESCchk ,selectchk, insertchk ,deletechk ,createchk, dropchk, updatechk, alterchk , selectdefaultchk; 
	Color color;
	ButtonGroup checkButG , checkButG1 ;

	String text , createAddcc, createAddIS;
	DefaultTableModel model;
	String getCulName, getCulName1;
	
	Vector<String> tableNamesV;
	Vector<String> vecCulName = new Vector<String>();
	Vector<Vector> tableMetaData = new Vector<Vector>();
    Vector<String> v1;
	Vector<String> vecCulName1;
	Vector<JTextField> textArray1 = new Vector<JTextField>();

	String url , id , pass; 
	Connection con;
	Statement st;
	PreparedStatement prstUserTableName;
    String Metadata;
	String createTableCheck; 
	ImageIcon selectI = new ImageIcon("image\\45.png");
    String sqlQuery;
	static int viewcount = 0;
    int rowCount = 0;
	int culCount , culCount1 , col ,row;
	
	JAE1(String url , String id , String pass){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url , id , pass); 
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		}catch(ClassNotFoundException ce){
			pln("ClassNotFoundException");
			ce.printStackTrace();
		}catch(SQLException se){
			pln("SQLException");
			se.printStackTrace();
		}
	 userTableName(); 
	 tableData(tableNamesV.get(0)); 
	 init(); 
	 addTextField(); 
	 Actions();
	}

	void setUI(){
	     setTitle("재순이의 디비관리");
		 setBounds(100 , 100 , 1700 , 800);
		 setVisible(true);
		 setResizable(false);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		 setLocationRelativeTo(null); 	
	}

	void init(){
        GradientPaint gp = new GradientPaint(0,10,Color.BLACK,0,70,Color.ORANGE);
		Random rand = new Random();
		cp = getContentPane();
		model = new DefaultTableModel();
		jtable1 = new JTable(model);
		JScrollPane sp = new JScrollPane(jtable1);
		model.setDataVector(tableMetaData,vecCulName);
		sp.setBorder(new LineBorder(Color.GRAY));

		textField2Label = new JLabel(new ImageIcon("image\\search.png"));
		textField2Label.setOpaque(true); // 투명
		textField2Label.setFont(new Font("나눔바른고딕", Font.BOLD, 17));
		textField2Label.setHorizontalAlignment(SwingConstants.CENTER);
	    textField2Label.setForeground(Color.BLACK);
		textField2Label.setBackground(Color.WHITE);
			 
		columnnameLabel = new JLabel(new ImageIcon("image\\columnname.png"));
		columnnameLabel.setOpaque(true);
		columnnameLabel.setFont(new Font("나눔바른고딕", Font.BOLD, 17));
		columnnameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		columnnameLabel.setForeground(Color.BLACK);
		columnnameLabel.setBackground(Color.WHITE);
		 
		tablenameLabel = new JLabel(new ImageIcon("image\\45.png"));
		tablenameLabel.setOpaque(true);
		tablenameLabel.setFont(new Font("나눔바른고딕", Font.BOLD, 17));
		tablenameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tablenameLabel.setForeground(Color.BLACK);
		tablenameLabel.setBackground(Color.WHITE);

		selectLabel = new JLabel(new ImageIcon("image\\sql.png"));
		selectLabel.setOpaque(true); 
		selectLabel.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
		selectLabel.setHorizontalAlignment(SwingConstants.CENTER);
		selectLabel.setForeground(Color.BLACK);
		selectLabel.setBackground(Color.WHITE);
		 
		insertLabel = new JLabel(new ImageIcon("image\\se.png"));
		insertLabel.setOpaque(true);
		insertLabel.setFont(new Font("나눔바른고딕", Font.BOLD, 17));
		insertLabel.setHorizontalAlignment(SwingConstants.CENTER);
		insertLabel.setBackground(Color.WHITE);
	    insertLabel.setForeground(Color.BLACK);
		
		jp1 = new JPanel(new GridLayout(3,3 , 7, 7));
		jp4 = new JPanel(new GridLayout(1,2 , 7 , 7));
		jp2 = new JPanel(new GridLayout(1,culCount ,1, 1));
		jp3 = new JPanel(new GridLayout(5,1 , 2 , 2));
		
		checkpa = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		checkpa2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		checkpa3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		checkpa4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		checkLa1 = new JLabel();
		checkLa2 = new JLabel("검색 내 정렬을 사용하시려면 체크 후 검색하세요 ->");
		checkLa2.setHorizontalAlignment(SwingConstants.RIGHT);
		tCombo = new JComboBox(tableNamesV);
		t1Combo = new JComboBox(vecCulName);

		textField2 = new JTextField();
		textField1 =  new JTextField();
	    textField3 = new JTextField();

		cp.add(jp1, BorderLayout.NORTH);	 
		cp.add(jp3, BorderLayout.SOUTH);
		cp.add(sp, BorderLayout.CENTER);
	 
		jp1.add(tablenameLabel);
		jp1.add(columnnameLabel);
		jp1.add(textField2Label); 
	    jp1.add(tCombo);
		jp1.add(t1Combo);
		jp1.add(textField2);	
		jp4.add(selectLabel);
 
		panel_Btn = new JPanel();
        panel_Btn.setPreferredSize(new Dimension(10, 43));
        panel_Btn.setAutoscrolls(true);
        panel_Btn.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		selectdefaultchk = new JCheckBox("기본 검색");
		selectchk = new JCheckBox("SELECT(검색)");
		insertchk = new JCheckBox("INSERT(추가)"); 
        deletechk = new JCheckBox("DELETE(삭제)");
		updatechk = new JCheckBox("UPDATE(변경)");
	    createchk = new JCheckBox("CREATE(생성)"); 
	    dropchk = new JCheckBox("DROP(삭제)"); 
	    alterchk = new JCheckBox("ALTER(변경)"); 
		ASCchks  = new JCheckBox("정렬 없음"); 
		DESCchk  = new JCheckBox("정렬(내림) "); 
		ASCchk   = new JCheckBox("정렬(오름) "); 

		checkButG = new ButtonGroup();
        checkButG.add(selectdefaultchk);
		checkButG.add(selectchk);
		checkButG.add(insertchk);
		checkButG.add(deletechk);
		checkButG.add(updatechk);
		checkButG.add(createchk);
		checkButG.add(dropchk);
		checkButG.add(alterchk);
		
		checkButG1 = new ButtonGroup();
		checkButG1.add(ASCchks);
		checkButG1.add(DESCchk);
		checkButG1.add(ASCchk);
		

		checkpa.add(selectdefaultchk);
		checkpa.add(selectchk);
		checkpa.add(insertchk);
		checkpa.add(updatechk);
		checkpa3.add(deletechk);
		checkpa3.add(createchk);
		checkpa3.add(dropchk);
		checkpa3.add(alterchk);
		checkpa2.add(ASCchks);
		checkpa2.add(DESCchk);
		checkpa2.add(ASCchk);
		checkpa4.add(checkLa1);	
				
		jp3.add(insertLabel);
		jp3.add(jp2);
		jp3.add(jp4);
		jp3.add(textField3);
		jp3.add(panel_Btn);
		jp4.add(checkpa);
		jp1.add(checkpa4);
		jp1.add(checkLa2);
		jp1.add(checkpa2);
		jp4.add(checkpa3);
		
		
		fieldB = new JButton(" TABLE SELECT ");
        fieldB.setHorizontalTextPosition(SwingConstants.CENTER);
        fieldB.setPreferredSize(new Dimension(150, 30));
        fieldB.setFocusPainted(false);
        fieldB.setFont(new Font("나눔바른고딕", Font.BOLD, 16));
        fieldB.setAlignmentX(Component.CENTER_ALIGNMENT);
        fieldB.setForeground(Color.WHITE);
        fieldB.setBackground(new Color(0x333CCC));
        fieldB.setBorder(null);
        panel_Btn.add(fieldB);

		viewB = new JButton("SQL EXECUTE (실행)");  
        viewB.setHorizontalTextPosition(SwingConstants.CENTER);
        viewB.setPreferredSize(new Dimension(200, 30));
        viewB.setFocusPainted(false);
        viewB.setFont(new Font("나눔바른고딕", Font.BOLD, 16));
        viewB.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewB.setForeground(Color.WHITE);
        viewB.setBackground(new Color(0x336CCC));
        viewB.setBorder(null);
        panel_Btn.add(viewB);

		setInsert = new JButton(" INSERT SETTING ");
        setInsert.setHorizontalTextPosition(SwingConstants.CENTER);
        setInsert.setPreferredSize(new Dimension(150, 30));
        setInsert.setFocusPainted(false);
        setInsert.setFont(new Font("나눔바른고딕", Font.BOLD, 16));
        setInsert.setAlignmentX(Component.CENTER_ALIGNMENT);
        setInsert.setForeground(Color.WHITE);
        setInsert.setBackground(new Color(0x336CCC));
        setInsert.setBorder(null);
        panel_Btn.add(setInsert);

		insertB = new JButton(" INSERT ");
        insertB.setHorizontalTextPosition(SwingConstants.LEFT);
        insertB.setPreferredSize(new Dimension(120, 30));
        insertB.setFocusPainted(false);
        insertB.setFont(new Font("나눔바른고딕", Font.BOLD, 16));
        insertB.setAlignmentX(Component.CENTER_ALIGNMENT);
        insertB.setForeground(Color.WHITE);
        insertB.setBackground(new Color(0x336CCC));
        insertB.setBorder(null);
        panel_Btn.add(insertB);

		deleteB = new JButton(" DELETE ");
        deleteB.setHorizontalTextPosition(SwingConstants.CENTER);
        deleteB.setPreferredSize(new Dimension(120, 30));
        deleteB.setFocusPainted(false);
        deleteB.setFont(new Font("나눔바른고딕", Font.BOLD, 16));
        deleteB.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteB.setForeground(Color.WHITE);
        deleteB.setBackground(new Color(0x336CCC));
        deleteB.setBorder(null);
        panel_Btn.add(deleteB);
		
		updateB = new JButton(" UPDATE ");
        updateB.setHorizontalTextPosition(SwingConstants.CENTER);
        updateB.setPreferredSize(new Dimension(120, 30));
        updateB.setFocusPainted(false);
        updateB.setFont(new Font("나눔바른고딕", Font.BOLD, 16));
        updateB.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateB.setForeground(Color.WHITE);
        updateB.setBackground(new Color(0x336CCC));
        updateB.setBorder(null);
        panel_Btn.add(updateB);	
			
		orderByB = new JButton(" 정렬 ");
        orderByB.setHorizontalTextPosition(SwingConstants.CENTER);
        orderByB.setPreferredSize(new Dimension(150, 30));
        orderByB.setFocusPainted(false);
        orderByB.setFont(new Font("나눔바른고딕", Font.BOLD, 16));
        orderByB.setAlignmentX(Component.CENTER_ALIGNMENT);
        orderByB.setForeground(Color.WHITE);
		orderByB.setBackground(new Color(0x336CCC));
        orderByB.setBorder(null);
        panel_Btn.add(orderByB);

		exitB = new JButton(" EXIT ");
        exitB.setHorizontalTextPosition(SwingConstants.CENTER);
        exitB.setPreferredSize(new Dimension(150, 30));
        exitB.setFocusPainted(false);
        exitB.setFont(new Font("나눔바른고딕", Font.BOLD, 16));
        exitB.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitB.setForeground(Color.WHITE);
        exitB.setBackground(new Color(0x333CCC));
        exitB.setBorder(null);
        panel_Btn.add(exitB);
		//ASCchks.setEnabled(false);

		setUI();	
	}

	void userTableName(){
		 ResultSet rs = null;
		 String userTableNamesql ="select table_name from user_tables"; 
		 try{
			 rs = st.executeQuery(userTableNamesql);
			 rs.last();
			 int rowCount = rs.getRow();
			 tableNamesV = new Vector<String>();
			 rs.beforeFirst();
		     while(rs.next()){
			   String getTableName = rs.getString("TABLE_NAME");
			   tableNamesV.add(getTableName);
			 }
		 }catch(SQLException se){
			JOptionPane.showMessageDialog(null, " ※ 예외사항 SQLException : "+se+" ", "기본 키 또는 값 불명", JOptionPane.OK_OPTION);
			se.printStackTrace();
		 }finally{
			try{	
				rs.close();
			}catch(SQLException se){}
		}	
	}    	
	void tableData(String startname){
		String getTableSQL ="select * from "+startname;
		ResultSet rs = null;
		try{
			rs = st.executeQuery(getTableSQL);
			ResultSetMetaData rsmd = rs.getMetaData();
			    culCount = rsmd.getColumnCount();
				for(int i=1; i<=culCount;i++){
				String cn = rsmd.getColumnName(i);
				vecCulName.add(cn);
				}
				while(rs.next()){
				     v1 = new Vector<String>();
					 for(int i=1; i<=culCount; i++){
						Metadata = rs.getString(i); 
						v1.add(Metadata);
					}
					tableMetaData.add(v1);
				}
		}catch(SQLException se){
			JOptionPane.showMessageDialog(null, " ※ 예외사항 SQLException : "+se+" ", "기본 키 또는 값 불명", JOptionPane.OK_OPTION);
			se.printStackTrace();
		}finally{
			 try{	
				rs.close();
			 }catch(SQLException se){}
		}	
	}
   
   String selectPK(String select1Tname1){  
		String selectPK1 = "SELECT column_name FROM all_cons_columns WHERE constraint_name = (SELECT constraint_name FROM all_constraints WHERE UPPER(table_name) = UPPER('"+select1Tname1+"') AND CONSTRAINT_TYPE ='P')";
	    ResultSet rs = null;
			 try{
				 rs = st.executeQuery(selectPK1);
    		     while(rs.next()){
				 getCulName1 = rs.getString("COLUMN_NAME");
				 }
			 }catch(SQLException se){
				JOptionPane.showMessageDialog(null, " ※ 예외사항 SQLException : "+se+" ", "기본 키 또는 값 불명", JOptionPane.OK_OPTION);
				se.printStackTrace();
			 }finally{
			 try{	
				rs.close();
			 }catch(SQLException se){}
			 }return getCulName1;
	}
/*	
	String selectOrderBy(String startname2){
		String culSelect = t1Combo.getSelectedItem().toString();
		String CCtname = tCombo.getSelectedItem().toString();
		String getTableSQL ="select * from "+ startname2;
		ResultSet rs = null;
		String OrderByColumnName = null;
		int culCount1 = 0;
		try{
			rs = st.executeQuery(getTableSQL);
			ResultSetMetaData rsmd = rs.getMetaData();
		    int culCount2 = rsmd.getColumnCount();
				for(int i=1; i<=culCount;i++){
					String cn = rsmd.getColumnName(i);
				   	if(cn == culSelect){
						OrderByColumnName =cn;
					}
				}
		}catch(SQLException se){
			JOptionPane.showMessageDialog(null, " ※ 예외사항 SQLException : "+se+" ", "기본 키 또는 값 불명", JOptionPane.OK_OPTION);
			se.printStackTrace();
		}finally{
			try{	
				rs.close();
			}catch(SQLException se){}
		}return OrderByColumnName;      	
	}
*/
	void OrderBytableData(String startname){
		String culSelect = t1Combo.getSelectedItem().toString();
		String getTableSQL ="select * from "+startname+" order by "+culSelect+"";
		ResultSet rs = null;
		try{
			rs = st.executeQuery(getTableSQL);
			ResultSetMetaData rsmd = rs.getMetaData();
			    culCount = rsmd.getColumnCount();
				for(int i=1; i<=culCount;i++){
					String cn = rsmd.getColumnName(i);
					vecCulName.add(cn);
				}
				while(rs.next()){
				     v1 = new Vector<String>();
					 for(int i=1; i<=culCount; i++){
						Metadata = rs.getString(i); 
						v1.add(Metadata);
					}
					tableMetaData.add(v1);
				}
		}catch(SQLException se){
			JOptionPane.showMessageDialog(null, " ※ 예외사항 SQLException : "+se+" ", "기본 키 또는 값 불명", JOptionPane.OK_OPTION);
			se.printStackTrace();
		}finally{
			 try{	
				rs.close();
			 }catch(SQLException se){}
		}	
	}

	void OrderBytableDataDESC(String startname){
		String culSelect = t1Combo.getSelectedItem().toString();
		String getTableSQL ="select * from "+startname+" order by "+culSelect+" DESC";
		ResultSet rs = null;
		try{
			rs = st.executeQuery(getTableSQL);
			ResultSetMetaData rsmd = rs.getMetaData();
			    culCount = rsmd.getColumnCount();
				for(int i=1; i<=culCount;i++){
					String cn = rsmd.getColumnName(i);
					vecCulName.add(cn);
				}
				while(rs.next()){
				     v1 = new Vector<String>();
					 for(int i=1; i<=culCount; i++){
						Metadata = rs.getString(i); 
						v1.add(Metadata);
					}
					tableMetaData.add(v1);
				}
		}catch(SQLException se){
			JOptionPane.showMessageDialog(null, " ※ 예외사항 SQLException : "+se+" ", "기본 키 또는 값 불명", JOptionPane.OK_OPTION);
			se.printStackTrace();
		}finally{
			 try{	
				rs.close();
			 }catch(SQLException se){}
		}	
	}
	
	
	void OrderBytableDataDESCField2(String startname , String sele){
		String culSelect = t1Combo.getSelectedItem().toString();
		String getTableSQL ="select * from "+startname+" where "+dataTypeSelect(startname,culSelect)+" like '%"+sele+"%' order by "+culSelect+" DESC";
		ResultSet rs = null;
		try{
			rs = st.executeQuery(getTableSQL);
			ResultSetMetaData rsmd = rs.getMetaData();
			    culCount = rsmd.getColumnCount();
				for(int i=1; i<=culCount;i++){
					String cn = rsmd.getColumnName(i);
					vecCulName.add(cn);
				}
				while(rs.next()){
				     v1 = new Vector<String>();
					 for(int i=1; i<=culCount; i++){
						Metadata = rs.getString(i); 
						v1.add(Metadata);
					}
					tableMetaData.add(v1);
				}
		}catch(SQLException se){
			JOptionPane.showMessageDialog(null, " ※ 예외사항 SQLException : "+se+" ", "기본 키 또는 값 불명", JOptionPane.OK_OPTION);
			se.printStackTrace();
		}finally{
			 try{	
				rs.close();
			 }catch(SQLException se){}
		}	
	}
	
	void viewOrderBytableDataField2(String sele){
		String culSelect = t1Combo.getSelectedItem().toString();
		String getTableSQL ="select * from "+ viewN +" where "+ culSelect +" like '%"+sele+"%' order by "+culSelect;
		ResultSet rs = null;
		try{
			rs = st.executeQuery(getTableSQL);
			ResultSetMetaData rsmd = rs.getMetaData();
			    culCount = rsmd.getColumnCount();
				for(int i=1; i<=culCount;i++){
					String cn = rsmd.getColumnName(i);
					vecCulName.add(cn);
				}
				while(rs.next()){
				     v1 = new Vector<String>();
					 for(int i=1; i<=culCount; i++){
						Metadata = rs.getString(i); 
						v1.add(Metadata);
					}
					tableMetaData.add(v1);
				}
		}catch(SQLException se){
			JOptionPane.showMessageDialog(null, " ※ 예외사항 SQLException : "+se+" ", "기본 키 또는 값 불명", JOptionPane.OK_OPTION);
			se.printStackTrace();
		}finally{
			 try{	
				rs.close();
				pln("1");
			 }catch(SQLException se){}
		}pln(""+getTableSQL);	
	}


	void viewOrderBytableDataDESCField2(String sele){
		String culSelect = t1Combo.getSelectedItem().toString();
		String getTableSQL ="select * from "+ viewN +" where "+ culSelect +" like '%"+sele+"%' order by "+culSelect+" DESC";
		ResultSet rs = null;
		try{
			rs = st.executeQuery(getTableSQL);
			ResultSetMetaData rsmd = rs.getMetaData();
			    culCount = rsmd.getColumnCount();
				for(int i=1; i<=culCount;i++){
					String cn = rsmd.getColumnName(i);
					vecCulName.add(cn);
				}
				while(rs.next()){
				     v1 = new Vector<String>();
					 for(int i=1; i<=culCount; i++){
						Metadata = rs.getString(i); 
						v1.add(Metadata);
					}
					tableMetaData.add(v1);
				}
		}catch(SQLException se){
			JOptionPane.showMessageDialog(null, " ※ 예외사항 SQLException : "+se+" ", "기본 키 또는 값 불명", JOptionPane.OK_OPTION);
			se.printStackTrace();
		}finally{
			 try{	
				rs.close();
				pln("1");
			 }catch(SQLException se){}
		}pln(""+getTableSQL);	
	}

		/*
			
	void viewfieldSelect(String sele){ 
		String cul2 = t1Combo.getSelectedItem().toString();
		String getTableSQL ="select * from "+viewN+" where "+cul2+" like '%"+sele+"%'";
		ResultSet rs = null;
		try{
			rs = st.executeQuery(getTableSQL);
			ResultSetMetaData rsmd = rs.getMetaData();
			    culCount = rsmd.getColumnCount();
				for(int i=1; i<=culCount;i++){
				String cn = rsmd.getColumnName(i);
				vecCulName.add(cn);
				}
				while(rs.next()){
				     v1 = new Vector<String>();
					 for(int i=1; i<=culCount; i++){
						Metadata = rs.getString(i); 
						v1.add(Metadata);
					}
					tableMetaData.add(v1);
				}
		}catch(SQLException se){
			se.printStackTrace();
		} finally{
			  try{	 
			rs.close();
		 }catch(SQLException se){}
		}
	}
		*/


	void OrderBytableDataField2(String startname , String sele){
		String culSelect = t1Combo.getSelectedItem().toString();
		String getTableSQL ="select * from "+startname+" where "+dataTypeSelect(startname,culSelect)+" like '%"+sele+"%' order by "+culSelect;
		ResultSet rs = null;
		try{
			rs = st.executeQuery(getTableSQL);
			ResultSetMetaData rsmd = rs.getMetaData();
			    culCount = rsmd.getColumnCount();
				for(int i=1; i<=culCount;i++){
					String cn = rsmd.getColumnName(i);
					vecCulName.add(cn);
				}
				while(rs.next()){
				     v1 = new Vector<String>();
					 for(int i=1; i<=culCount; i++){
						Metadata = rs.getString(i); 
						v1.add(Metadata);
					}
					tableMetaData.add(v1);
				}
		}catch(SQLException se){
			JOptionPane.showMessageDialog(null, " ※ 예외사항 SQLException : "+se+" ", "기본 키 또는 값 불명", JOptionPane.OK_OPTION);
			se.printStackTrace();
		}finally{
			 try{	
				rs.close();
			 }catch(SQLException se){}
		}	
	}

	void selectColumName(String startname1){  
		String CCtname = tCombo.getSelectedItem().toString();
		String getTableSQL ="select * from "+startname1;
		ResultSet rs = null;
		int culCount1 = 0;
		vecCulName1 = new Vector<String>();
		try{
			rs = st.executeQuery(getTableSQL);
			ResultSetMetaData rsmd = rs.getMetaData();
		    int culCount2 = rsmd.getColumnCount();
				for(int i=1; i<=culCount;i++){
				String cn = rsmd.getColumnName(i);
				vecCulName1.add(cn);
				}
		}catch(SQLException se){
			JOptionPane.showMessageDialog(null, " ※ 예외사항 SQLException : "+se+" ", "기본 키 또는 값 불명", JOptionPane.OK_OPTION);
			se.printStackTrace();
		}finally{
			try{	
				rs.close();
			}catch(SQLException se){}
		}        
		cp.revalidate(); 
        cp.repaint();	
	}
	
	int selectPKindex(){ 
		String CCtname = tCombo.getSelectedItem().toString();
		String CpK = selectPK(CCtname);
		int cc1 = jtable1.getColumnCount();
		int s1 = 0;	
		for(int w=0;w<cc1;w++){
			String PKindex = jtable1.getColumnName(w);
				if(getCulName1 == null){    
				 s1 = 0;
				}else if(CpK.equals(PKindex)){
				 s1 = w; 
				}else{
				 s1 = 0;
				}
		}return s1; 
   }
   
   void viewdropp(){
		try{
			int i = 0;
			boolean ffbool = true;
			while(ffbool){
				String sql = "drop view "+viewN;
				pln(sql+"");
				i++;
				st.execute(sql);
				if(views.size()==0){
					ffbool =false;
				}
				}viewcount=0;
		    views.clear();				
			}catch(SQLException se){}
	}

	void addTextField(){
		jp2.removeAll();
		model.setDataVector(tableMetaData,vecCulName);
		int cc = jtable1.getColumnCount();
		int rw = jtable1.getRowCount();
		int selectC = selectPKindex();
		for(int i2=0; i2<cc; i2++){
				textArray1.add(new JTextField());
				jp2.add(textArray1.get(i2));
				textArray1.get(i2).setText("");		
					if(selectC==i2){
						 textArray1.get(i2).setEnabled(false);
						 jp2.add(textArray1.get(i2));
					}
			}
        cp.revalidate(); 
        cp.repaint();
		insertB.setEnabled(false);
	}

	void insertBec(){
		String CCtname = tCombo.getSelectedItem().toString();
		String sql1 = "insert into "+CCtname+" values (";
		try{
			int cc1 = jtable1.getColumnCount();
			int selectpPK = selectPKindex(); 
			boolean selectpPKbool = textArray1.get(selectpPK).getText().equals("");
			boolean textb = textArray1.get(0).getText().equals("");
			
			if(selectpPKbool==true && textb==true ){
					JOptionPane.showMessageDialog(null, "키 값이 없어 INSERT가 실패되었습니다.", "기본 키 또는 값 불명", JOptionPane.OK_OPTION);
					return;	
			}
			if(selectpPKbool==true){
					JOptionPane.showMessageDialog(null, "키 값이 없어 INSERT가 실패되었습니다.", "기본 키 또는 값 불명", JOptionPane.OK_OPTION);
					return;
			}
			for(int u=0;u<cc1;u++){
				if(u==(cc1-1)){
				sql1 = sql1 +"'"+ textArray1.get(u).getText()+"'" +")";
				st.executeUpdate(sql1);	
				break;
				}
			sql1 = sql1 +"'"+ textArray1.get(u).getText() +"'"+ ", ";   
			}
			
			for(int dd =0;dd<cc1;dd++){
				textArray1.get(dd).setText("");
			}
		}catch(SQLException se){
          JOptionPane.showMessageDialog(null, " ※ 예외사항 SQLException : "+se+" ", "기본 키 또는 값 불명", JOptionPane.OK_OPTION);
		}
	
	}	
	void updateBec(){
		String CCtname = tCombo.getSelectedItem().toString();
		selectColumName(CCtname); 
		String CpK = selectPK(CCtname);
		int cc1 = jtable1.getColumnCount();
		int s = selectPKindex();
		String cul = "";
		String contentCul = textField2.getText();	
		String sql1 = "update "+CCtname+" set ";
	    try{
			for(int u=0;u<cc1;u++){
				if(s==u){
					continue;
				}	
				if(u==(cc1-1)){
					if(UpdateDataTypeSelect(CCtname , vecCulName1.get(u)))
						if(vecCulName1.get(u).equalsIgnoreCase("SYSDATE")){
							sql1 = sql1 + vecCulName1.get(u)+" = "+textArray1.get(u).getText()+" where "+CpK+" = '"+textArray1.get(s).getText()+"'";	
						}else{
							sql1 = sql1 + vecCulName1.get(u)+" = '"+textArray1.get(u).getText()+"' where "+CpK+" = '"+textArray1.get(s).getText()+"'";
						}
					else{
						sql1 = sql1 + vecCulName1.get(u)+" = '"+textArray1.get(u).getText()+"' where "+CpK+" = '"+textArray1.get(s).getText()+"'";
					}
					pln(":" + sql1);
					st.executeUpdate(sql1);	
					break;
				}
				
				if(UpdateDataTypeSelect(CCtname , vecCulName1.get(u))){
						if(textArray1.get(u).getText().equalsIgnoreCase("SYSDATE")){
							sql1 = sql1 +""+ vecCulName1.get(u)+" = "+textArray1.get(u).getText()+ " , "; 
						}else{
							sql1 = sql1 +""+ vecCulName1.get(u)+" = '"+textArray1.get(u).getText()+ "' , "; 
						}
					}else{
							sql1 = sql1 +""+ vecCulName1.get(u)+" = '"+textArray1.get(u).getText()+ "' , "; 
					}

			}
			for(int dd =0;dd<cc1;dd++){
				textArray1.get(dd).setText("");
			}
			pln(":" + sql1);
			st.executeUpdate(sql1);	
		}catch(SQLException se){
			pln("update"+se);
		}
		textField2.setText("");
		textArray1.clear();
		vecCulName1.clear();
	}
	
	boolean UpdateDataTypeSelect(String tnames , String cul2s){
		String typeSelect = "SELECT data_type from all_tab_columns where table_name='"+tnames+"' and column_name = '"+cul2s+"'";
		ResultSet rs = null;
		String culs = null;
		boolean Dateset = false;
		try{
			rs = st.executeQuery(typeSelect);
			while(rs.next()){
				String typeS = rs.getString(1);
				if(typeS.equalsIgnoreCase("DATE")){
					Dateset = true;
					return Dateset;
				}else{
					Dateset = false;
					return Dateset;
				}
			}
		}catch(SQLException se){
			pln("datatype 오류" + se);
		}
		return Dateset;
	}

	void deleteBec(){
		String CCtname = tCombo.getSelectedItem().toString();
		String CpK = selectPK(CCtname);
		int cc1 = jtable1.getColumnCount();
		int s = selectPKindex();
		String cul = t1Combo.getSelectedItem().toString();
		String contentCul = textField2.getText();	 
		String sql1 = "delete from "+CCtname+" where "+CpK+" = '"+textArray1.get(s).getText()+"'";
	    try{
			st.executeUpdate(sql1);	
		}catch(SQLException se){
			JOptionPane.showMessageDialog(null, " ※ 예외사항 SQLException : "+se+" ", "기본 키 또는 값 불명", JOptionPane.OK_OPTION);
		}
		textField2.setText("");
		textArray1.clear();
	}

	void selectBec(){	
		sqlQuery = textField3.getText();
		StringBuilder sb = new StringBuilder();
		String []insertsplit = sqlQuery.split("\\(");
		String insertsplit2 = insertsplit[0];
	    String []insertsplit3 = insertsplit2.split(" ");
		String insertsplit4 = insertsplit3[0]; 
		String sqllplus1 = insertsplit[0];
		int indexSele = 0;
		if(insertsplit4.equalsIgnoreCase("insert")){
			try{
				pln(""+sqlQuery);
				st.executeQuery(sqlQuery);
				textField3.setText("");
			}catch(SQLException se1){
				pln(""+se1);
			}
	
		}else if(insertsplit4.equalsIgnoreCase("select")){	
			ResultSet rs = null;
			String sqlQuery2 = textField3.getText();
			try{
				rs = st.executeQuery(sqlQuery2);
				ResultSetMetaData rsmd = rs.getMetaData();
					culCount = rsmd.getColumnCount();
					for(int i=1; i<=culCount;i++){
					String cn = rsmd.getColumnName(i);
					vecCulName.add(cn);
					}
					while(rs.next()){      
						 v1 = new Vector<String>();
						 for(int i=1; i<=culCount; i++){
							if(i==indexSele){
								Metadata = rs.getString(i);
								Date s = Date.valueOf(Metadata); 
								v1.add(s.toString());
							}else{
							Metadata = rs.getString(i); 
							v1.add(Metadata);
							}
						}
					tableMetaData.add(v1);
					}
			}catch(SQLException se){
				JOptionPane.showMessageDialog(null, " ※ 예외사항 SQLException : "+se+" ", "기본 키 또는 값 불명", JOptionPane.OK_OPTION);
				se.printStackTrace();
			}finally{
				try{	
				rs.close();
				}catch(SQLException se){}
			}textField3.setText("");
		}else if(insertsplit4.equalsIgnoreCase("delete")){
			try{
			st.executeQuery(sqllplus1);
			textField3.setText("");
			}catch(SQLException se1){
			JOptionPane.showMessageDialog(null, " ※ 예외사항 SQLException : "+se1+" ", "기본 키 또는 값 불명", JOptionPane.OK_OPTION);
			}
		}else if(insertsplit4.equalsIgnoreCase("update")){
			try{
				st.executeQuery(sqllplus1);
				textField3.setText("");	
			}catch(SQLException se4){
				JOptionPane.showMessageDialog(null, " ※ 예외사항 SQLException : "+se4+" ", "기본 키 또는 값 불명", JOptionPane.OK_OPTION);
			}
		}
	}

	void ddlTable(){	
		String createQ = textField3.getText();           
		String []createAddI = createQ.split(" ");          
		String createAddIScda = createAddI[0];
		createAddIS = createAddI[2];
		if(createAddIScda.equalsIgnoreCase("create")){
			if(createAddIS.contains("(")){ 
				int cF = createAddIS.indexOf("(");         
				createAddcc = createAddIS.substring(0 , cF);
				tCombo.addItem(createAddcc);
				try{
					st.execute(createQ);
				}catch(SQLException se){
						tCombo.removeItem(createAddcc);
						tCombo.removeItem(createAddIS);
						JOptionPane.showMessageDialog(null, " ※ 예외사항 SQLException : "+se+" ", "기본 키 또는 값 불명", JOptionPane.OK_OPTION);
				}textField3.setText("");
			}else{
				tCombo.addItem(createAddIS);
				try{
					st.execute(createQ);
				}catch(SQLException se4){
						tCombo.removeItem(createAddcc);
						tCombo.removeItem(createAddIS);
						JOptionPane.showMessageDialog(null, " ※ 예외사항 SQLException : "+se4+" ", "기본 키 또는 값 불명", JOptionPane.OK_OPTION);
				}textField3.setText("");
			}
		}else if(createAddIScda.equalsIgnoreCase("drop")){
			String upcreateAddIS = createAddIS.toUpperCase();
			String lowcreateAddIS = createAddIS.toLowerCase();
			tCombo.removeItem(upcreateAddIS);  
			tCombo.removeItem(lowcreateAddIS);
			tCombo.removeItem(createAddIS);
			try{
				st.execute(createQ);
			}catch(SQLException se2){
				JOptionPane.showMessageDialog(null, " ※ 예외사항 SQLException : "+se2+" ", "삭제 실패", JOptionPane.OK_OPTION);
		}textField3.setText("");
		}else if(createAddIScda.equalsIgnoreCase("alter")){
			try{
				st.execute(createQ);
			}catch(SQLException se3){
				JOptionPane.showMessageDialog(null, " ※ 예외사항 SQLException : "+se3+" ", "변경 실패", JOptionPane.OK_OPTION);
		}textField3.setText("");
		}
	}

	String dataTypeSelect(String tnames , String cul2s){
		String typeSelect = "SELECT data_type from all_tab_columns where table_name='"+tnames+"' and column_name = '"+cul2s+"'";
		ResultSet rs = null;
		String culs = null;
		try{
			rs = st.executeQuery(typeSelect);
			while(rs.next()){
				String typeS = rs.getString(1);
				if(typeS.equalsIgnoreCase("DATE")){
					culs = "to_char("+cul2s+"  , 'YYYYMMDD')";
					return culs;
				}
			}
		}catch(SQLException se){
			pln("datatype 오류" + se);
		}return cul2s;
	}


	void fieldSelect(String startname , String sele){ 
		String cul2 = t1Combo.getSelectedItem().toString();
		String getTableSQL ="select * from "+startname + " where "+ dataTypeSelect(startname,cul2)+" like '%"+sele+"%'";
		ResultSet rs = null;
		try{
			rs = st.executeQuery(getTableSQL);
			ResultSetMetaData rsmd = rs.getMetaData();
			    culCount = rsmd.getColumnCount();
				for(int i=1; i<=culCount;i++){
				String cn = rsmd.getColumnName(i);
				vecCulName.add(cn);
				}
				while(rs.next()){
				     v1 = new Vector<String>();
					 for(int i=1; i<=culCount; i++){
						Metadata = rs.getString(i); 
						v1.add(Metadata);
					}
					tableMetaData.add(v1);
				}
		}catch(SQLException se){
			se.printStackTrace();
		} finally{
			  try{	 
			rs.close();
		 }catch(SQLException se){}
		}
	}

	String viewN;
    Vector<String> views = new Vector<String>(); 
	void viewcreate(boolean rbool){
		String viewSelect = "";
		while(rbool){
			views.add("view"+(Integer)viewcount);
			viewN = "view"+(Integer)viewcount;
			viewSelect = "create view "+viewN+" as "+ sqlQuery;

			try{
				st.execute(viewSelect);
			}catch(SQLException se){
			    pln("viewCreate" + se);
			}
			viewcount++;
			rbool = false;
		}
	}

	void viewfieldSelect(String sele){ 
		String cul2 = t1Combo.getSelectedItem().toString();
		String getTableSQL ="select * from "+viewN+" where "+cul2+" like '%"+sele+"%'";
		ResultSet rs = null;
		try{
			rs = st.executeQuery(getTableSQL);
			ResultSetMetaData rsmd = rs.getMetaData();
			    culCount = rsmd.getColumnCount();
				for(int i=1; i<=culCount;i++){
				String cn = rsmd.getColumnName(i);
				vecCulName.add(cn);
				}
				while(rs.next()){
				     v1 = new Vector<String>();
					 for(int i=1; i<=culCount; i++){
						Metadata = rs.getString(i); 
						v1.add(Metadata);
					}
					tableMetaData.add(v1);
				}
		}catch(SQLException se){
			se.printStackTrace();
		} finally{
			  try{	 
			rs.close();
		 }catch(SQLException se){}
		}
	}
	void closeAll(){
		try{
		if(st != null)st.close();
		if(con != null)	con.close();
		if(prstUserTableName != null) prstUserTableName.close();
		}catch(SQLException se){
		}
	}


 void Actions(){

      insertB.addActionListener(new ActionListener (){
         @Override
         public void actionPerformed(ActionEvent e){
           String rtname = tCombo.getSelectedItem().toString();
           vecCulName.clear();
           tableMetaData.clear();
           insertBec();
           tableData(rtname); 
           model.setDataVector(tableMetaData,vecCulName); 
           addTextField();
           cp.revalidate();
           cp.repaint();   
         }
      });

      fieldB.addActionListener(new ActionListener (){ 
         @Override
         public void actionPerformed(ActionEvent e){
            vecCulName.clear();
            tableMetaData.clear();
            textArray1.clear();
            tCombo.setEnabled(true);
            String rtname = tCombo.getSelectedItem().toString();
            tableData(rtname); 
            selectColumName(rtname);
            t1Combo.setSelectedIndex(0);
            tCombo.requestFocus();
            model.setDataVector(tableMetaData,vecCulName);
            addTextField();
			selectchk.setSelected(false);
			selectdefaultchk.setSelected(true);
			DESCchk.setSelected(false);
			ASCchk.setSelected(false);
			ASCchks.setSelected(true);	
            cp.revalidate(); 
            cp.repaint(); 
          }
      });
   
      jtable1.addMouseListener(new MouseListener(){
          public void mouseReleased(MouseEvent me){
          }
          public void mousePressed(MouseEvent me){
          }
          public void mouseExited(MouseEvent me){
          }
          public void mouseEntered(MouseEvent me){
          }
          @Override
          public void mouseClicked(MouseEvent me){
            row = jtable1.getSelectedRow();
            col = jtable1.getSelectedColumn();
            addTextField();
            int ssc = jtable1.getColumnCount();
               try{
                  for(int i=0;i<ssc;i++){
                     Object getValue = jtable1.getValueAt(row,i); 
                     String k = (String)getValue;
                     k.trim();
                     if(k.length()==19 && k.substring(k.length()-9 , k.length()).equals(" 00:00:00")){
                        String k2 = k.substring(0, k.length()-9);
                        java.sql.Date d=  java.sql.Date.valueOf(k2);
                        textArray1.get(i).setText(k2);
                     }else{
                     textArray1.get(i).setText(k);
                     }
                  }
               }catch(NullPointerException ne){
            }
            jtable1.revalidate(); 
            jtable1.repaint();
          }      
      });
   
      updateB.addActionListener(new ActionListener (){
         @Override
         public void actionPerformed(ActionEvent e){
           vecCulName.clear();
           tableMetaData.clear();
           updateBec();
           String rtname = tCombo.getSelectedItem().toString();
           tableData(rtname);
           model.setDataVector(tableMetaData,vecCulName); 
           addTextField();
           cp.revalidate(); 
           cp.repaint();
          }   
      });
      
      deleteB.addActionListener(new ActionListener (){
         @Override
         public void actionPerformed(ActionEvent e){
           vecCulName.clear();
           tableMetaData.clear();
           deleteBec();
           String rtname = tCombo.getSelectedItem().toString();
           tableData(rtname);
           model.setDataVector(tableMetaData,vecCulName); 
           addTextField();
           cp.revalidate(); 
           cp.repaint();
          }
      });
   
      exitB.addActionListener(new ActionListener (){  
         @Override
         public void actionPerformed(ActionEvent e){
               viewdropp();
               closeAll();
               System.exit(0);
          }   
      });   
   
      selectchk.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
               if( e.getStateChange() != 0 ) {
                  viewdropp();
                  tCombo.setEnabled(true);
               }
            }
      });

      textField2.addKeyListener(new KeyListener(){
         public void keyPressed(KeyEvent e){
         }
         public void keyReleased(KeyEvent e){} 
         @Override
         public void keyTyped(KeyEvent e){
            if(selectchk.isSelected() && ASCchk.isSelected() ){
				   vecCulName.clear();
				   tableMetaData.clear();
				   textArray1.clear();
				   int kCode=e.getKeyChar();
				   if(kCode==10){
					   String ss1 = Character.toString(kCode);
					   String kk12 = textField2.getText()+ss1;
					   kk12 = (textField2.getText()+ss1).substring(0 , kk12.length()-1);
					   if(e.paramString().indexOf("Backspace") != -1){
						  kk12 = (textField2.getText()+ss1).substring(0 , kk12.length()-1);
					   }
					   viewcreate(true);
					   viewOrderBytableDataField2(kk12);   
					   model.setDataVector(tableMetaData,vecCulName);
					   jtable1.revalidate(); 
					   jtable1.repaint();
					   addTextField();
					   cp.revalidate(); 
					   cp.repaint();
						   textField2.requestFocus();	
					   }
					   String ss1 = Character.toString(kCode);
					   String kk12 = textField2.getText()+ss1;
				  if(e.paramString().indexOf("Backspace") != -1){
						 kk12 = (textField2.getText()+ss1).substring(0 , kk12.length()-1);
					   }
					   viewOrderBytableDataField2(kk12); 
					   model.setDataVector(tableMetaData,vecCulName); 
					   jtable1.revalidate(); 
					   jtable1.repaint();
					   addTextField();
					   cp.revalidate(); 
					   cp.repaint();
					   textField2.requestFocus();
			}else if(selectchk.isSelected() && DESCchk.isSelected() ){
				   vecCulName.clear();
				   tableMetaData.clear();
				   textArray1.clear();
				   int kCode=e.getKeyChar();
				   if(kCode==10){
					   String ss1 = Character.toString(kCode);
					   String kk12 = textField2.getText()+ss1;
					   kk12 = (textField2.getText()+ss1).substring(0 , kk12.length()-1);
					   if(e.paramString().indexOf("Backspace") != -1){
						  kk12 = (textField2.getText()+ss1).substring(0 , kk12.length()-1);
					   }
					   //viewcreate(true);
					   viewOrderBytableDataDESCField2(kk12);   
					   model.setDataVector(tableMetaData,vecCulName);
					   jtable1.revalidate(); 
					   jtable1.repaint();
					   addTextField();
					   cp.revalidate(); 
					   cp.repaint();
						   textField2.requestFocus();	
					   }
					   String ss1 = Character.toString(kCode);
					   String kk12 = textField2.getText()+ss1;
				  if(e.paramString().indexOf("Backspace") != -1){
						 kk12 = (textField2.getText()+ss1).substring(0 , kk12.length()-1);
					   }
					   viewOrderBytableDataDESCField2(kk12); 
					   model.setDataVector(tableMetaData,vecCulName); 
					   jtable1.revalidate(); 
					   jtable1.repaint();
					   addTextField();
					   cp.revalidate(); 
					   cp.repaint();
					   textField2.requestFocus();
            }else if(ASCchk.isSelected()){
				   vecCulName.clear();
				   tableMetaData.clear();
				   textArray1.clear();
				   int kCode=e.getKeyChar(); 
				   if(kCode==10){
					   String ss1 = Character.toString(kCode);
					   String kk12 = textField2.getText()+ss1;
					   String rtname1 = tCombo.getSelectedItem().toString();
					   kk12 = (textField2.getText()+ss1).substring(0 , kk12.length()-1);
					   if(e.paramString().indexOf("Backspace") != -1){
							kk12 = (textField2.getText()+ss1).substring(0 , kk12.length()-1);
					   }
					   OrderBytableDataField2(rtname1 ,kk12);   
					   model.setDataVector(tableMetaData,vecCulName);
					   jtable1.revalidate(); 
					   jtable1.repaint();
					   addTextField();
					   cp.revalidate(); 
					   cp.repaint();
					   textField2.requestFocus();		
				   }else{
					   String ss1 = Character.toString(kCode);
					   String kk12 = textField2.getText()+ss1;
					   String rtname1 = tCombo.getSelectedItem().toString();
					   if(e.paramString().indexOf("Backspace") != -1){
						  kk12 = (textField2.getText()+ss1).substring(0 , kk12.length()-1);
					   }
					   OrderBytableDataField2(rtname1 ,kk12); 
					   model.setDataVector(tableMetaData,vecCulName);
					   addTextField();
					   jtable1.revalidate(); 
					   jtable1.repaint();
					   cp.revalidate(); 
					   cp.repaint();
					   textField2.requestFocus();
				   }
			//OrderBytableDataDESCField2(String startname , String sele)	
			}else if(DESCchk.isSelected()){
					   vecCulName.clear();
					   tableMetaData.clear();
					   textArray1.clear();
					   int kCode=e.getKeyChar(); 
					   if(kCode==10){
					   String ss1 = Character.toString(kCode);
					   String kk12 = textField2.getText()+ss1;
					   String rtname1 = tCombo.getSelectedItem().toString();
					   kk12 = (textField2.getText()+ss1).substring(0 , kk12.length()-1);
					   if(e.paramString().indexOf("Backspace") != -1){
							kk12 = (textField2.getText()+ss1).substring(0 , kk12.length()-1);
					   }
					   OrderBytableDataDESCField2(rtname1 ,kk12);   
					   model.setDataVector(tableMetaData,vecCulName);
					   jtable1.revalidate(); 
					   jtable1.repaint();
					   addTextField();
					   cp.revalidate(); 
					   cp.repaint();
					   textField2.requestFocus();		
				   }else{
					   String ss1 = Character.toString(kCode);
					   String kk12 = textField2.getText()+ss1;
					   String rtname1 = tCombo.getSelectedItem().toString();
					   if(e.paramString().indexOf("Backspace") != -1){
						  kk12 = (textField2.getText()+ss1).substring(0 , kk12.length()-1);
					   }
					   OrderBytableDataDESCField2(rtname1 ,kk12); 
					   model.setDataVector(tableMetaData,vecCulName);
					   addTextField();
					   jtable1.revalidate(); 
					   jtable1.repaint();
					   cp.revalidate(); 
					   cp.repaint();
					   textField2.requestFocus();
				   }
			}else if(selectchk.isSelected()){
				   vecCulName.clear();
				   tableMetaData.clear();
				   textArray1.clear();
				   int kCode=e.getKeyChar();
				   if(kCode==10){
					   String ss1 = Character.toString(kCode);
					   String kk12 = textField2.getText()+ss1;
					   kk12 = (textField2.getText()+ss1).substring(0 , kk12.length()-1);
					   if(e.paramString().indexOf("Backspace") != -1){
						  kk12 = (textField2.getText()+ss1).substring(0 , kk12.length()-1);
					   }
					   viewfieldSelect(kk12);  
					   model.setDataVector(tableMetaData,vecCulName);
					   jtable1.revalidate(); 
					   jtable1.repaint();
					   addTextField();
					   cp.revalidate(); 
					   cp.repaint();
					   textField2.requestFocus();	
				   }
				   String ss1 = Character.toString(kCode);
				   String kk12 = textField2.getText()+ss1;
				   if(e.paramString().indexOf("Backspace") != -1){
					 kk12 = (textField2.getText()+ss1).substring(0 , kk12.length()-1);
				   }
				   viewfieldSelect(kk12);  
				   model.setDataVector(tableMetaData,vecCulName); 
				   jtable1.revalidate(); 
				   jtable1.repaint();
				   addTextField();
				   cp.revalidate(); 
				   cp.repaint();
				   textField2.requestFocus();   
			}else{
               vecCulName.clear();
               tableMetaData.clear();
               textArray1.clear();
               int kCode=e.getKeyChar(); 
			   if(kCode==10){
				   String ss1 = Character.toString(kCode);
				   String kk12 = textField2.getText()+ss1;
				   String rtname1 = tCombo.getSelectedItem().toString();
				   kk12 = (textField2.getText()+ss1).substring(0 , kk12.length()-1);
				   if(e.paramString().indexOf("Backspace") != -1){
						kk12 = (textField2.getText()+ss1).substring(0 , kk12.length()-1);
				   }
				   fieldSelect(rtname1 ,kk12);   
				   model.setDataVector(tableMetaData,vecCulName);
				   jtable1.revalidate(); 
				   jtable1.repaint();
				   addTextField();
				   cp.revalidate(); 
				   cp.repaint();
				   textField2.requestFocus();		
			   }else{
				   String ss1 = Character.toString(kCode);
				   String kk12 = textField2.getText()+ss1;
				   String rtname1 = tCombo.getSelectedItem().toString();
				   if(e.paramString().indexOf("Backspace") != -1){
					  kk12 = (textField2.getText()+ss1).substring(0 , kk12.length()-1);
               }
               fieldSelect(rtname1 ,kk12);  
               model.setDataVector(tableMetaData,vecCulName);
               addTextField();
               jtable1.revalidate(); 
               jtable1.repaint();
               cp.revalidate(); 
               cp.repaint();
               textField2.requestFocus();
			   }
            }
         }
      });
	  //viewOrderBytableDataField2(String sele)
	  orderByB.addMouseListener(new MouseListener(){
          public void mouseReleased(MouseEvent me){
          }
          public void mousePressed(MouseEvent me){
          }
          public void mouseExited(MouseEvent me){
          }
          public void mouseEntered(MouseEvent me){
          }
          @Override
          public void mouseClicked(MouseEvent me){
			  int buttons = me.getButton();
			  if(me.getClickCount()==1){
					vecCulName.clear();
					tableMetaData.clear();
					textArray1.clear();
					tCombo.setEnabled(true);
					String rtname = tCombo.getSelectedItem().toString();
					OrderBytableData(rtname);
					selectColumName(rtname);
					tCombo.requestFocus();
					model.setDataVector(tableMetaData,vecCulName);
					addTextField();
					cp.revalidate(); 
					cp.repaint(); 
			 }else if(me.getClickCount()==2){
					vecCulName.clear();
					tableMetaData.clear();
					textArray1.clear();
					tCombo.setEnabled(true);
					String rtname = tCombo.getSelectedItem().toString();
					OrderBytableDataDESC(rtname);
					selectColumName(rtname);
					tCombo.requestFocus();
					model.setDataVector(tableMetaData,vecCulName);
					addTextField();
					cp.revalidate(); 
					cp.repaint(); 
			}
          }      
      });

      viewB.addActionListener(new ActionListener (){ // select 실행 
            @Override
            public void actionPerformed(ActionEvent e){
               if(selectchk.isSelected()){ 
              int Rs = JOptionPane.showConfirmDialog(null, "이 검색문을 실행할 시 자동으로 커밋됩니다. 실행하시겠습니까?", "DDL 확인", JOptionPane.YES_NO_OPTION);
                  if(Rs == JOptionPane.YES_OPTION){   
                  viewdropp();
                  vecCulName.clear();
                  tableMetaData.clear();
                  textArray1.clear();
                  selectBec();
                  viewcreate(true);
                  tCombo.setEnabled(false);
                  model.setDataVector(tableMetaData,vecCulName);  
                  addTextField();
                  jtable1.revalidate(); 
                  jtable1.repaint();
                  cp.revalidate(); 
                  cp.repaint();
                  textField3.requestFocus();
              }
              if(Rs == JOptionPane.NO_OPTION){
               return;
              }
               }
               if(insertchk.isSelected()){ 
                  vecCulName.clear();
                  tableMetaData.clear();
                  textArray1.clear();   
                  selectBec(); 
                  model.setDataVector(tableMetaData,vecCulName);  
                  addTextField();
                  jtable1.revalidate(); 
                  jtable1.repaint();
                  cp.revalidate(); 
                  cp.repaint();
                  textField3.requestFocus();
               }
               if(updatechk.isSelected()){ 
                  vecCulName.clear();
                  tableMetaData.clear();
                  textArray1.clear();   
                  selectBec();
                  model.setDataVector(tableMetaData,vecCulName); 
                  addTextField();
                  jtable1.revalidate(); 
                  jtable1.repaint();
                  cp.revalidate(); 
                  cp.repaint();
                  textField3.requestFocus();                 
               }
               if(deletechk.isSelected()){ 
                  vecCulName.clear();
                  tableMetaData.clear();
                  textArray1.clear();   
                  selectBec();
                  model.setDataVector(tableMetaData,vecCulName);  
                  addTextField();
                  cp.revalidate(); 
                  cp.repaint();
                  textField3.requestFocus();        
               }

               if(createchk.isSelected()){ 
                  vecCulName.clear();
                  tableMetaData.clear();
                  textArray1.clear();
                  int Rs = JOptionPane.showConfirmDialog(null, "테이블을 정말로 변경하실 건가요?", "DDL 확인", JOptionPane.YES_NO_OPTION);
                  if(Rs == JOptionPane.YES_OPTION){   
                        vecCulName.clear();
                        tableMetaData.clear();
                        textArray1.clear();
                        ddlTable(); 
                        userTableName();
                        model.setDataVector(tableMetaData,vecCulName);  
                        addTextField();
                        cp.revalidate(); 
                        cp.repaint();
                        textField3.requestFocus();
                        JOptionPane.showMessageDialog(null, "테이블에 대한 처리 완료. 재검색하세요.", "생성완료", JOptionPane.OK_OPTION);
                  }else if(Rs == JOptionPane.NO_OPTION){
                         return;
                  }
               }
               if(dropchk.isSelected()){ 
                  vecCulName.clear();
                  tableMetaData.clear();
                  textArray1.clear();
                  int Rs = JOptionPane.showConfirmDialog(null, "테이블을 정말로 변경하실 건가요?", "DDL 확인", JOptionPane.YES_NO_OPTION);
                  if(Rs == JOptionPane.YES_OPTION){   
                        vecCulName.clear();
                        tableMetaData.clear();
                        textArray1.clear();
                        ddlTable(); 
                        userTableName();
                        model.setDataVector(tableMetaData,vecCulName);  
                        addTextField();
                        cp.revalidate(); 
                        cp.repaint();
                        textField3.requestFocus();
                        JOptionPane.showMessageDialog(null, "테이블에 대한 처리 완료. 재검색하세요.", "생성완료", JOptionPane.OK_OPTION);
                  }else if(Rs == JOptionPane.NO_OPTION){
                         return;
                  }
               }

               if(alterchk.isSelected()){ 
                  vecCulName.clear();
                  tableMetaData.clear();
                  textArray1.clear();
                  int Rs = JOptionPane.showConfirmDialog(null, "테이블을 정말로 변경하실 건가요?", "DDL 확인", JOptionPane.YES_NO_OPTION);
                  if(Rs == JOptionPane.YES_OPTION){   
                        vecCulName.clear();
                        tableMetaData.clear();
                        textArray1.clear();
                        ddlTable();
                        userTableName();
                        model.setDataVector(tableMetaData,vecCulName);  
                        addTextField();
                        cp.revalidate(); 
                        cp.repaint();
                        textField3.requestFocus();
                        JOptionPane.showMessageDialog(null, "테이블에 대한 처리 완료. 재검색하세요.", "생성완료", JOptionPane.OK_OPTION);
                  }else if(Rs == JOptionPane.NO_OPTION){
                         return;
                  }
               }
               if(selectdefaultchk.isSelected()){
                  String rtname1 = tCombo.getSelectedItem().toString();
                  vecCulName.clear();
                  tableMetaData.clear();
                  textArray1.clear();
                  tCombo.setEnabled(true);
                  tableData(rtname1); 
                  model.setDataVector(tableMetaData,vecCulName);  
                  addTextField();
                  cp.revalidate(); 
                  cp.repaint();
                  textField3.requestFocus();
				}
				DESCchk.setSelected(false);
				ASCchk.setSelected(false);
				ASCchks.setSelected(true);	
			}
      });

      setInsert.addActionListener(new ActionListener (){
         @Override
         public void actionPerformed(ActionEvent e){
          int selectC = selectPKindex();
          textArray1.get(selectC).requestFocus();
          insertB.requestFocus();
          boolean ss = textArray1.get(selectC).isRequestFocusEnabled();
          boolean ss1 = insertB.isRequestFocusEnabled();
          if(ss){
            textArray1.get(selectC).setEnabled(true);
            if(ss1){
               insertB.setEnabled(true);
            }
          }    
          }
      });

      addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent e) {
            viewdropp();
            try{
               int i = 0;
               boolean ffbool = true;
               while(ffbool){
                  String viewnamm = "view"+(Integer)i;
                  String sql = "drop view "+viewnamm;
                  i++;
                  st.execute(sql);
                  if(views.size()==0){
                  ffbool =false;
                  }
               }viewcount=0;
               views.clear();            
            }catch(SQLException se){}
            System.exit(0);
         }
      });

       tCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
               if( e.getStateChange() == ItemEvent.SELECTED ) {
                  String rtname1 = tCombo.getSelectedItem().toString();
                  vecCulName.clear();
                  tableMetaData.clear();
                  textArray1.clear();
                  tableData(rtname1);
                  t1Combo.setSelectedIndex(0);
                  model.setDataVector(tableMetaData,vecCulName);  
                  addTextField();
                  cp.revalidate(); 
                  cp.repaint();
                  textField3.requestFocus();
               }
            }
      });

   }   
   void p(String str){
      System.out.print(str);
   }
   void pln(String str){
      System.out.println(str);
   }
}

/*

	void selectBec(){	
		sqlQuery = textField3.getText();
		StringBuilder sb = new StringBuilder();
		String []insertsplit = sqlQuery.split("\\(");
		String insertsplit2 = insertsplit[0];
	    String []insertsplit3 = insertsplit2.split(" ");
		String insertsplit4 = insertsplit3[0]; 
		String sqllplus1 = insertsplit[0];
		int indexSele = 0;
		if(insertsplit4.equalsIgnoreCase("insert")){
			String insertsplitd1 = insertsplit[1]; 
		    String []insertsplitd2 = insertsplitd1.split(",");
		    String sqllplus = insertsplit[0] + "(";
			 for(int i=0;i<insertsplitd2.length;i++){
				  insertsplitd2[i].trim();
			   if(insertsplitd2[i].startsWith(" ")){
				  String RR1 = insertsplitd2[i].substring(1 , insertsplitd2[i].length());
				  insertsplitd2[i] = RR1;
				  if(i==insertsplitd2.length-1){
					  String sssk = insertsplitd2[i];
					  String sssk1 = sssk.substring(0 , sssk.length()-1);
					  sssk1.trim();
					  if(sssk1.startsWith("SYSDATE")){ 
						LocalDate date = LocalDate.now();
						insertsplitd2[i] = date.toString();
						String kk = "'"+insertsplitd2[i]+"')";
						indexSele = i;
						insertsplitd2[i] = kk;
					  }
				  }
				  if(insertsplitd2[i].startsWith("SYSDATE")){
					  LocalDate date = LocalDate.now();
					  insertsplitd2[i] = date.toString();
					  String kk = "'"+insertsplitd2[i]+"'";
					  indexSele = i;
					  insertsplitd2[i] = kk;
				  }
				  if(i==insertsplitd2.length-1){
				  sqllplus = sqllplus + insertsplitd2[i];
				  }else{
				  sqllplus = sqllplus + insertsplitd2[i] + ",";
				  }
			 }else{
				 if(i==insertsplitd2.length-1){
					  String sssk = insertsplitd2[i];
					  String sssk1 = sssk.substring(0 , sssk.length()-1);
					  sssk1.trim(); 
					  if(sssk1.startsWith("SYSDATE")){ 
						LocalDate date = LocalDate.now();
						insertsplitd2[i] = date.toString();
						String kk = insertsplitd2[i]+")";
						indexSele = i;
						insertsplitd2[i] = kk;
					  }else{  
					  }
				 if(insertsplitd2[i].startsWith("SYSDATE")){
					  LocalDate date = LocalDate.now();
					  indexSele = i;
					  insertsplitd2[i] = date.toString();
					  String kk = "'"+insertsplitd2[i]+"'";
					  insertsplitd2[i] = kk;
				  }
				 }
			if(i==insertsplitd2.length-1){
					  sqllplus = sqllplus + insertsplitd2[i];
			}else{
					  sqllplus = sqllplus + insertsplitd2[i] + ",";
			}
		}
		}
		try{
			pln(""+sqllplus);
			st.executeQuery(sqllplus);
			textField3.setText("");
		}catch(SQLException se1){
			pln(""+se1);
		}

*/