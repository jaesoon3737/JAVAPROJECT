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
//�̾Ƴ����ִ°� , �������������ִ� ���̺�,�⺻Ű,�⺻Ű���ε���,�÷���,�÷���,�ο��,pk�̸�,���̺���
// JOptionPane.showMessageDialog(null, "�Է��Ͻ� ������ ", "�⺻ Ű �Ǵ� �� �Ҹ�", JOptionPane.OK_OPTION);

//���� 1 , ������Ʈ �� �������̺��ϸ� �ε����ƿ� ����  // �ذ�
//���� 2 , �μ�Ʈ �Ϸ��� �ؽ�Ʈ�ʵ� �����־����.  //textArray[k33].setEnabled(true); �μ�Ʈ�Ҷ� ���� �ְԸ����.// �ذ�
//���� 3 , select �� ���� ������ ���̺���õ� �ֱ� // �Ϸ� = 13�� �ذ�������.
//���� 4 , �ͼ��� �߸� ���â ����� ���ϰ��ϱ� // �Ϸ� 
//���� 5 , insert get �ε� ���� // �ذ�
//���� 6 , �����ϴ� �ý��� ��ư ����� // �ذ�
//���� 7 , ���ڻ��ٲٰ� // �ذ�
//���� 8 , �������� ���ι� , �׷����, �������� �����ұ�� ����� // �ذ�
//���� 9 , insertsetting �ϴ°� ������ insert Ȱ��ȭ, �⺻Ű Ȱ��ȭ // �ذ�
//���� 10, �⺻Ű�� PK�� ���������� ���ʻ� ù��°�� ���°� �ƴ϶� ���������� ��� �� �� �־ PK�ε������� �̾Ƽ� �� �÷��� �´� ���� ��Ȱ��ȭ // �ذ�
//���� 11, �μ�Ʈ ������ PK �ߺ������� �߻��ϸ� �������µ� , �װ� �ذ��ϱ����ؼ� InsertSetting ��ư�� ������ Ȱ��ȭ �ǰ� , �μ�Ʈ �Ŀ��� �ٽ� ��Ȱ��ȭ //�ذ�
//���� 12, Ű�� ���� ���̺� �μ�Ʈ ������ �� ���� �ο찡 �����. // �ذ�
//���� 13, �˻��� ���� �ؽ�Ʈ�ʵ忡 ��Ŀ���� �ǰ��� �޺��ڽ��� Enable(true) �� �ϰ� , �� �� listListener �Ἥ ���� �޼ҵ带 �ҷ����������. // �ذ�
//���� 14, ũ������Ʈ�� ���� DDL �� �޺��ڽ��� �������� �� �̺�Ʈ�߻����� �⺻���� �ְ� ����. ��ư�� ���ϴ� �������� �ٲܼ� �ֳ�.. // �ذ�
//���� 15,  ����Ʈ ���� ���ܿö� �߶󼭰����;���. // �ذ�
//���� 16, �˻�â �齺���̽� , ũ������Ʈ ���̺��ϸ� �Ⱥ��� // �ذ�
//���� 18, ������������� DATE ���� ������ sysdate �νĹ����ΰŰ�����..  // �ذ� -> jtable �� �ٷ� �������� �ؼ� �������°� �־��ٰ� �ٽ� ���������
//���� 19, ���̺� ����� add Combobox �ǽð� ���� // ����Ǽ� 1 , " " split �Ἥ �迭�� �̸� ���ܿ��� , ����Ǽ� 2 ���ܿ����� ( �ٿ����� ������� ���� substring �� ��. //�ذ�
//���� 20, ���̺� ����� �ڵ� Ŀ�� ������ �����ϱ� , yes �϶� ����� no �� �� ���� ��ų ��, // �ذ�
        //���� 21, commit �κ� ���� ��,
        //���� 22, id �α��� �ϴ� �κ� ���� ��
//���� 23 SYSDATE ���� �����ӤӤӤӤӤӤӤӤӤӤӤ� //  �ذ�
// �����ؼ� - ����� - �䶯�ܼ� �ű⼭ �˻� // �Ϸ�
// ���ߵ� ����Ʈ�ߴµ� �˻���;; ��ư���� if�� �ؽ�Ʈ�ʵ� �о null �̸� ���� �ȵǰ� ����. // �ذ�
// �Ʒ� �ؽ�Ʈ���� �˻��ϸ� �⺻Ű���� 
// �� ������ �� �Է�â ����� ���̸� �����ϰ� �ϰ� , �ϰ��ڿ� �װ� �޾ƿͼ� ����  ,  �⺻Ű ��ġ�� �̻���. �⺻Ű ���¾� �˻��ϸ�, //�Ϸ�












public class JAE1 extends JFrame {
	
	Container cp;
	JTable jtable1;
	JComboBox tCombo, t1Combo;
	JTextField textField1 , textField2 ,textField3 ;
	JPanel jp1, jp2 , jp3 , panel_Btn , jp4 , jp5 ,jp6 ,bp1,bp2 ,checkpa;
	JButton Binsert , Bupdate , Bdelete ;
	JLabel tablenameLabel , columnnameLabel , textField2Label , selectLabel , insertLabel , DdlSelectLabel , checkLa;
	JButton selectExecute , fieldB , insertB , updateB , deleteB , dropB , selectB , createB ,BColumnFieldUpdate , setInsert , exitB , viewB  , viewBColse ; 
	Color color;
	
	String text , createAddcc, createAddIS;
	DefaultTableModel model;
	String getCulName, getCulName1;
	
	Vector<String> tableNamesV;
	Vector<String> vecCulName = new Vector<String>();
	Vector<Vector> tableMetaData = new Vector<Vector>();
    Vector<String> v1;
	Vector<String> vecCulName1;
	Vector<JTextField> textArray1 = new Vector<JTextField>();

	String url = "jdbc:oracle:thin:@127.0.0.1:1521:JAVA";
	Connection con;
	Statement st;
	PreparedStatement prstUserTableName;
    String Metadata ;
    String id = "scott";
	String pass = "tiger";
	String createTableCheck; 
	ImageIcon selectI = new ImageIcon("image\\45.png");
    JCheckBox selectchk, insertchk ,deletechk ,createchk, dropchk, updatechk, alterchk , selectdefaultchk; 
	//DatabaseMetaData db; db = con.getMetaData(); db.getprimaryKey <<
    String sqlQuery;
	static int viewcount = 0;
    int rowCount = 0;
	int culCount , culCount1 , col ,row;
	JAE1(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url , id , pass); // �⺻ �� scott , tiger
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			//db = con.getMetaData(); �����⸸�� ����Ʈ �������̸Ӹ�Ű 
		   // prstUserTableName = con.prepareStatement(userTableNamesql);
		}catch(ClassNotFoundException ce){
			pln("ClassNotFoundException");
			ce.printStackTrace();
		}catch(SQLException se){
			pln("SQLException");
			se.printStackTrace();
		}
	 userTableName(); // ���̺� ���� ��������
	 tableData(tableNamesV.get(0)); // Jtable ������ְ� 
	 init(); // ui
	 addTextField(); // �ʵ�갡������
	 Actions();// ������
	}

	void setUI(){
	     setTitle("������� ������");
		 setBounds(100 , 100 , 1700 , 800);
		 setVisible(true);
		 setResizable(false);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���°�
		 setLocationRelativeTo(null); // â ��� �����.
		
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

		//sp.setBorder(BorderFactory.createEmptyBorder(0 , 10 , 0 , 0));

		 //sp.setViewportView(jtable1);
		textField2Label = new JLabel(new ImageIcon("image\\search.png"));
		textField2Label.setOpaque(true); // ����
		textField2Label.setFont(new Font("�����ٸ����", Font.BOLD, 17));
		textField2Label.setHorizontalAlignment(SwingConstants.CENTER);
	    textField2Label.setForeground(Color.BLACK);
		textField2Label.setBackground(Color.WHITE);
		//textField2Label.setBorder(new LineBorder(Color.GRAY));
		 
		 
		columnnameLabel = new JLabel(new ImageIcon("image\\columnname.png"));
		columnnameLabel.setOpaque(true);
		columnnameLabel.setFont(new Font("�����ٸ����", Font.BOLD, 17));
		columnnameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		columnnameLabel.setForeground(Color.BLACK);
		columnnameLabel.setBackground(Color.WHITE);
		//columnnameLabel.setBorder(new LineBorder(Color.GRAY));
		 
		tablenameLabel = new JLabel(new ImageIcon("image\\45.png"));
		tablenameLabel.setOpaque(true);
		tablenameLabel.setFont(new Font("�����ٸ����", Font.BOLD, 17));
		tablenameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tablenameLabel.setForeground(Color.BLACK);
		tablenameLabel.setBackground(Color.WHITE);
		//tablenameLabel.setBorder(new LineBorder(Color.GRAY));

		selectLabel = new JLabel(new ImageIcon("image\\sql.png"));
		selectLabel.setOpaque(true); 
		selectLabel.setFont(new Font("�����ٸ����", Font.BOLD, 15));
		selectLabel.setHorizontalAlignment(SwingConstants.CENTER);
		selectLabel.setForeground(Color.BLACK);
		selectLabel.setBackground(Color.WHITE);
		//selectLabel.setBorder(new LineBorder(Color.BLACK)); 
		 
		insertLabel = new JLabel(new ImageIcon("image\\se.png"));
		insertLabel.setOpaque(true);
		insertLabel.setFont(new Font("�����ٸ����", Font.BOLD, 17));
		insertLabel.setHorizontalAlignment(SwingConstants.CENTER);
		insertLabel.setBackground(Color.WHITE);
	    insertLabel.setForeground(Color.BLACK);
		//insertLabel.setBorder(new LineBorder(Color.BLACK));
		/*
		DdlSelectLabel = new JLabel("2 . ����Ͻ� SQL�� �����ϼ���.  ��");
		DdlSelectLabel.setOpaque(true);
		DdlSelectLabel.setFont(new Font("�����ٸ����", Font.BOLD, 17));
		DdlSelectLabel.setHorizontalAlignment(SwingConstants.CENTER);
		DdlSelectLabel.setBackground(Color.WHITE);
	    DdlSelectLabel.setForeground(Color.BLACK);
		DdlSelectLabel.setBorder(new LineBorder(Color.BLACK));
		*/
		jp1 = new JPanel(new GridLayout(2,3 , 7, 7));
		jp4 = new JPanel(new GridLayout(1,2 , 7 , 7));
		jp2 = new JPanel(new GridLayout(1,culCount ,1, 1));
		jp3 = new JPanel(new GridLayout(5,1 , 2 , 2));
		 //jp5.setPreferredSize(new Dimension(20, 10));
		// jp6.setPreferredSize(new Dimension(20, 10));
		// jp1.setOpaque(true);
		//jp2.setOpaque(true);
		//jp3.setOpaque(false);
		checkpa = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		checkLa = new JLabel();
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
        //cp.add(panel_Btn, BorderLayout.SOUTH);
        panel_Btn.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		selectdefaultchk = new JCheckBox("�⺻ �˻�");
		selectchk = new JCheckBox("SELECT(�˻�)");
		insertchk = new JCheckBox("INSERT(�߰�)"); 
        deletechk = new JCheckBox("DELETE(����)");
		updatechk = new JCheckBox("UPDATE(����)");
	    createchk = new JCheckBox("CREATE(����)"); 
	    dropchk = new JCheckBox("DROP(����)"); 
	    alterchk = new JCheckBox("ALTER(����)"); 

		ButtonGroup checkButG = new ButtonGroup(); // check �ϳ����� �����ϰ� �����ϴ°�.
        checkButG.add(selectdefaultchk);
		checkButG.add(selectchk);
		checkButG.add(insertchk);
		checkButG.add(deletechk);
		checkButG.add(updatechk);
		checkButG.add(createchk);
		checkButG.add(dropchk);
		checkButG.add(alterchk);

		
		checkpa.add(selectdefaultchk);
		checkpa.add(selectchk);
		checkpa.add(insertchk);
		checkpa.add(updatechk);
		checkpa.add(deletechk);
		checkpa.add(createchk);
		checkpa.add(dropchk);
		checkpa.add(alterchk);

		jp3.add(insertLabel);
		jp3.add(jp2);
		jp3.add(jp4);
		jp3.add(textField3);
		jp3.add(panel_Btn);
		jp4.add(checkpa);

		fieldB = new JButton(" TABLE SELECT ");
        fieldB.setHorizontalTextPosition(SwingConstants.CENTER);
        fieldB.setPreferredSize(new Dimension(150, 30));
        fieldB.setFocusPainted(false);
        fieldB.setFont(new Font("�����ٸ����", Font.BOLD, 16));
        fieldB.setAlignmentX(Component.CENTER_ALIGNMENT);
        fieldB.setForeground(Color.WHITE);
        fieldB.setBackground(new Color(0x333CCC));
        fieldB.setBorder(null);
        panel_Btn.add(fieldB);
		
		viewB = new JButton("SQL EXECUTE (����)");  //  view ���� ������ �����ߴϱ� ���â���� ����� �ʿ��ϴٰ� �Ұ�., 
        viewB.setHorizontalTextPosition(SwingConstants.CENTER);
        viewB.setPreferredSize(new Dimension(200, 30));
        viewB.setFocusPainted(false);
        viewB.setFont(new Font("�����ٸ����", Font.BOLD, 16));
        viewB.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewB.setForeground(Color.WHITE);
        viewB.setBackground(new Color(0x336CCC));
        viewB.setBorder(null);
        panel_Btn.add(viewB);
		/*
		viewBColse = new JButton("CLOSE SELECT IN RE-SEARCH");  //  view ���� ������ �����ߴϱ� ���â���� ����� �ʿ��ϴٰ� �Ұ�., 
        viewBColse.setHorizontalTextPosition(SwingConstants.CENTER);
        viewBColse.setPreferredSize(new Dimension(200, 30));
        viewBColse.setFocusPainted(false);
        viewBColse.setFont(new Font("�����ٸ����", Font.BOLD, 16));
        viewBColse.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewBColse.setForeground(Color.WHITE);
        viewBColse.setBackground(new Color(0x336CCC));
        viewBColse.setBorder(null);
        panel_Btn.add(viewBColse);
*/
		setInsert = new JButton(" INSERT SETTING ");
        setInsert.setHorizontalTextPosition(SwingConstants.CENTER);
        setInsert.setPreferredSize(new Dimension(150, 30));
        setInsert.setFocusPainted(false);
        setInsert.setFont(new Font("�����ٸ����", Font.BOLD, 16));
        setInsert.setAlignmentX(Component.CENTER_ALIGNMENT);
        setInsert.setForeground(Color.WHITE);
        setInsert.setBackground(new Color(0x336CCC));
        setInsert.setBorder(null);
        panel_Btn.add(setInsert);

		insertB = new JButton(" INSERT ");
        insertB.setHorizontalTextPosition(SwingConstants.LEFT);
        insertB.setPreferredSize(new Dimension(120, 30));
        insertB.setFocusPainted(false);
        insertB.setFont(new Font("�����ٸ����", Font.BOLD, 16));
        insertB.setAlignmentX(Component.CENTER_ALIGNMENT);
        insertB.setForeground(Color.WHITE);
        insertB.setBackground(new Color(0x336CCC));
        insertB.setBorder(null);
        panel_Btn.add(insertB);

		deleteB = new JButton(" DELETE ");
        deleteB.setHorizontalTextPosition(SwingConstants.CENTER);
        deleteB.setPreferredSize(new Dimension(120, 30));
        deleteB.setFocusPainted(false);
        deleteB.setFont(new Font("�����ٸ����", Font.BOLD, 16));
        deleteB.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteB.setForeground(Color.WHITE);
        deleteB.setBackground(new Color(0x336CCC));
        deleteB.setBorder(null);
        panel_Btn.add(deleteB);
		
		updateB = new JButton(" UPDATE ");
        updateB.setHorizontalTextPosition(SwingConstants.CENTER);
        updateB.setPreferredSize(new Dimension(120, 30));
        updateB.setFocusPainted(false);
        updateB.setFont(new Font("�����ٸ����", Font.BOLD, 16));
        updateB.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateB.setForeground(Color.WHITE);
        updateB.setBackground(new Color(0x336CCC));
        updateB.setBorder(null);
        panel_Btn.add(updateB);	
		//new Color(rand.nextInt(0xFFFFFF))
		exitB = new JButton(" EXIT ");
        exitB.setHorizontalTextPosition(SwingConstants.CENTER);
        exitB.setPreferredSize(new Dimension(150, 30));
        exitB.setFocusPainted(false);
        exitB.setFont(new Font("�����ٸ����", Font.BOLD, 16));
        exitB.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitB.setForeground(Color.WHITE);
        exitB.setBackground(new Color(0x333CCC));
        exitB.setBorder(null);
        panel_Btn.add(exitB);

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
			JOptionPane.showMessageDialog(null, " �� ���ܻ��� SQLException : "+se+" ", "�⺻ Ű �Ǵ� �� �Ҹ�", JOptionPane.OK_OPTION);
			se.printStackTrace();
		 }finally{
			  try{	
				rs.close();
			  }catch(SQLException se){}
		}
		
	}
         	
	void tableData(String startname){ // ���̺� ������ ���� ������,
		String getTableSQL ="select * from "+startname;
		//selectColumName(startname);
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
			JOptionPane.showMessageDialog(null, " �� ���ܻ��� SQLException : "+se+" ", "�⺻ Ű �Ǵ� �� �Ҹ�", JOptionPane.OK_OPTION);
			se.printStackTrace();
		} finally{
			  try{	
			rs.close();
		 }catch(SQLException se){}
		}	
	}

   String selectPK(String select1Tname1){  // PK �÷� ���� �̱�
		String selectPK1 = "SELECT column_name FROM all_cons_columns WHERE constraint_name = (SELECT constraint_name FROM all_constraints WHERE UPPER(table_name) = UPPER('"+select1Tname1+"') AND CONSTRAINT_TYPE ='P')";
	    ResultSet rs = null;
			 try{
				 rs = st.executeQuery(selectPK1);
				 //int rowCount = rs.getRow();
    		     while(rs.next()){
				 getCulName1 = rs.getString("COLUMN_NAME");
				 }
			 }catch(SQLException se){
				JOptionPane.showMessageDialog(null, " �� ���ܻ��� SQLException : "+se+" ", "�⺻ Ű �Ǵ� �� �Ҹ�", JOptionPane.OK_OPTION);
				se.printStackTrace();
			 }finally{
			  try{	
				rs.close();
			  }catch(SQLException se){}
			 }return getCulName1;
	}

	void selectColumName(String startname1){  // ������Ʈ���� �����ϸ� vecCulname1 ���Ϳ� ��.
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
				//while(rs.next()){}
		}catch(SQLException se){
			JOptionPane.showMessageDialog(null, " �� ���ܻ��� SQLException : "+se+" ", "�⺻ Ű �Ǵ� �� �Ҹ�", JOptionPane.OK_OPTION);
			se.printStackTrace();
		} finally{
			  try{	
			rs.close();
		 }catch(SQLException se){}
		}        cp.revalidate(); 
        cp.repaint();
		
	}
	
	int selectPKindex(){  // PK �ε��� ���ϱ� 
		String CCtname = tCombo.getSelectedItem().toString();
		String CpK = selectPK(CCtname);
		int cc1 = jtable1.getColumnCount();
		int s1 = 0;	
		for(int w=0;w<cc1;w++){
			String PKindex = jtable1.getColumnName(w);
				if(getCulName1 == null){    // Ű�ε��� �˻� ����� ���� ���� �����������.
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
		//model.setDataVector(tableMetaData,vecCulName);
		insertB.setEnabled(false);
	}

	void insertBec(){
		String CCtname = tCombo.getSelectedItem().toString();
		String sql1 = "insert into "+CCtname+" values (";
		try{
			int cc1 = jtable1.getColumnCount();
			int selectpPK = selectPKindex(); // �⺻Ű�� ���ų�, �ؽ�Ʈ�� ��ü�� ������� �ź�
			boolean selectpPKbool = textArray1.get(selectpPK).getText().equals("");
			boolean textb = textArray1.get(0).getText().equals("");
			
			if(selectpPKbool==true && textb==true ){
					JOptionPane.showMessageDialog(null, "Ű ���� ���� INSERT�� ���еǾ����ϴ�.", "�⺻ Ű �Ǵ� �� �Ҹ�", JOptionPane.OK_OPTION);
					return;	
			}
			if(selectpPKbool==true){
					JOptionPane.showMessageDialog(null, "Ű ���� ���� INSERT�� ���еǾ����ϴ�.", "�⺻ Ű �Ǵ� �� �Ҹ�", JOptionPane.OK_OPTION);
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
          JOptionPane.showMessageDialog(null, " �� ���ܻ��� SQLException : "+se+" ", "�⺻ Ű �Ǵ� �� �Ҹ�", JOptionPane.OK_OPTION);
		}
	
	}	
	void updateBec(){
		String CCtname = tCombo.getSelectedItem().toString();
		selectColumName(CCtname); //vecCulname1 �ִ°ſ� ��
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
				sql1 = sql1 + vecCulName1.get(u)/*�÷���*/+" = '"+textArray1.get(u).getText()+"' where "+CpK+" = '"+textArray1.get(s).getText()+"'";
				st.executeUpdate(sql1);	
				break;
				}
			sql1 = sql1 +""+ vecCulName1.get(u)+" = '"+textArray1.get(u).getText()+ "' , ";   
			}
			for(int dd =0;dd<cc1;dd++){
				textArray1.get(dd).setText("");
			}
			st.executeUpdate(sql1);	
		}catch(SQLException se){
			pln("update"+se);
		}
		textField2.setText("");
		textArray1.clear();
		vecCulName1.clear();
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
			JOptionPane.showMessageDialog(null, " �� ���ܻ��� SQLException : "+se+" ", "�⺻ Ű �Ǵ� �� �Ҹ�", JOptionPane.OK_OPTION);
		}
		textField2.setText("");
		textArray1.clear();
	}
	/*
			try{
		String str_date = "11-June-07";
		DateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
		Date date = formatter.parse(str_date);
		
		}catch(ParseException pe){}
	
	
	*/
	//select e.empno , d.dname from emp  e, dept d where d.deptno = e.deptno
	//select e.empno , d.dname , d.loc from emp  e, dept d where e.deptno = (select deptno from dept where dname = 'ACCOUNTING') AND E.DEPTNO = D.DEPTNO
	void selectBec(){	
		sqlQuery = textField3.getText();
		StringBuilder sb = new StringBuilder();
		String []insertsplit = sqlQuery.split("\\(");
		String insertsplit2 = insertsplit[0];
	    String []insertsplit3 = insertsplit2.split(" ");
		String insertsplit4 = insertsplit3[0]; // ���̺��
		String sqllplus1 = insertsplit[0];
		int indexSele = 0;
		if(insertsplit4.equalsIgnoreCase("insert")){
			String insertsplitd1 = insertsplit[1]; 
		    String []insertsplitd2 = insertsplitd1.split(",");
		    String sqllplus = insertsplit[0] + "(";
			 for(int i=0;i<insertsplitd2.length;i++){
				  insertsplitd2[i].trim();
				  System.out.println("1 :" +insertsplitd2[i]);
			   if(insertsplitd2[i].startsWith(" ")){ //
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
		st.executeQuery(sqllplus);
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
				while(rs.next()){       //��������� ����  valueOf()
				     v1 = new Vector<String>();
					 for(int i=1; i<=culCount; i++){
						if(i==indexSele){
						    Metadata = rs.getString(i);
						    Date s = Date.valueOf(Metadata); // ���� Date �� �־�� �ذ��ϴµ�..
							v1.add(s.toString());
						}else{
						Metadata = rs.getString(i); 
						v1.add(Metadata);
						}
					}
				tableMetaData.add(v1);
				}
		}catch(SQLException se){
			JOptionPane.showMessageDialog(null, " �� ���ܻ��� SQLException : "+se+" ", "�⺻ Ű �Ǵ� �� �Ҹ�", JOptionPane.OK_OPTION);
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
		JOptionPane.showMessageDialog(null, " �� ���ܻ��� SQLException : "+se1+" ", "�⺻ Ű �Ǵ� �� �Ҹ�", JOptionPane.OK_OPTION);
		}
	}else if(insertsplit4.equalsIgnoreCase("update")){
	    try{
		st.executeQuery(sqllplus1);
		textField3.setText("");	
		}catch(SQLException se4){
		JOptionPane.showMessageDialog(null, " �� ���ܻ��� SQLException : "+se4+" ", "�⺻ Ű �Ǵ� �� �Ҹ�", JOptionPane.OK_OPTION);
		}
	}
	}
	/*update emp set ename = '���ߵȴ�' where empno = 888
		if(k.length()==19 && k.substring(k.length()-9 , k.length()).equals(" 00:00:00")){
							   String k2 = k.substring(0, k.length()-9);
							   java.sql.Date d=  java.sql.Date.valueOf(k2);
	
	*/ 
	void ddlTable(){	
		String createQ = textField3.getText();            // �޺��ڽ� �ٷιٲٱ�.
		String []createAddI = createQ.split(" ");          // ���ø����� �̸� ������
		String createAddIScda = createAddI[0];
		createAddIS = createAddI[2];
		if(createAddIScda.equalsIgnoreCase("create")){
			//boolean s = createAddIS.contains("(");
			if(createAddIS.contains("(")){ // ( �� �����Ҷ� ���꽺Ʈ���ؾ��ϴϱ�  
				int cF = createAddIS.indexOf("(");         // create ������ ���̺� �̸� ���ö� ( �پ ���� ©����ؼ� 
				createAddcc = createAddIS.substring(0 , cF);
				tCombo.addItem(createAddcc);
				try{
					st.execute(createQ);
				}catch(SQLException se){
						tCombo.removeItem(createAddcc);
						tCombo.removeItem(createAddIS);
						JOptionPane.showMessageDialog(null, " �� ���ܻ��� SQLException : "+se+" ", "�⺻ Ű �Ǵ� �� �Ҹ�", JOptionPane.OK_OPTION);
				}textField3.setText("");
			}else{
				tCombo.addItem(createAddIS);
				try{
					st.execute(createQ);
				}catch(SQLException se4){
						tCombo.removeItem(createAddcc);
						tCombo.removeItem(createAddIS);
						JOptionPane.showMessageDialog(null, " �� ���ܻ��� SQLException : "+se4+" ", "�⺻ Ű �Ǵ� �� �Ҹ�", JOptionPane.OK_OPTION);
				}textField3.setText("");
			}
		}else if(createAddIScda.equalsIgnoreCase("drop")){
			String upcreateAddIS = createAddIS.toUpperCase();
			String lowcreateAddIS = createAddIS.toLowerCase();
			tCombo.removeItem(upcreateAddIS); //���� �����ߴ��ִ� �������� �����ִ� �ִ� �� ������ �־ȴ��� �޴��� �ҹ��� �빮�ڿ�.. 
			tCombo.removeItem(lowcreateAddIS);
			tCombo.removeItem(createAddIS);
			pln(createAddIScda+"");
			try{
				st.execute(createQ);
			}catch(SQLException se2){
				JOptionPane.showMessageDialog(null, " �� ���ܻ��� SQLException : "+se2+" ", "���� ����", JOptionPane.OK_OPTION);
		}textField3.setText("");
		}else if(createAddIScda.equalsIgnoreCase("alter")){
			try{
				st.execute(createQ);
			}catch(SQLException se3){
				JOptionPane.showMessageDialog(null, " �� ���ܻ��� SQLException : "+se3+" ", "���� ����", JOptionPane.OK_OPTION);
		}textField3.setText("");
		}
	}
	
	void fieldSelect(String startname , String sele){ // ���̺� ������ ���� ������,
		String cul2 = t1Combo.getSelectedItem().toString();
		String getTableSQL ="select * from "+startname + " where "+ cul2+" like '%"+sele+"%'";
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
    Vector<String> views = new Vector<String>(); // �ϰ��� ���� Ŭ�������ֱ� , �������
	void viewcreate(boolean rbool){
		String viewSelect = "";
		while(rbool){
			views.add("view"+(Integer)viewcount);
			viewN = "view"+(Integer)viewcount;
			viewSelect = "create view "+viewN+" as "+ sqlQuery;

			try{
			pln(""+viewSelect);
			st.execute(viewSelect);
			}catch(SQLException se){
			  pln("��� ���������ϱ�" + se);
			}
			viewcount++;
			rbool = false;
		}
	}
/*	void viewCre(String viewN1){
			try{
			String viewSelect = "create view "+viewN1+" as "+ textField3.getText();
			pln(""+viewSelect);
			st.execute(viewSelect);
			
			}catch(SQLException se){
			  pln("��� ���������ϱ�" + se);
		select e.empno , d.dname from emp  e, dept d where d.deptno = e.deptno	}
	
	}*/
		
	void viewfieldSelect(String sele){ // ���̺� ������ ���� ������,
		
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
			st.close();
			con.close();
		}catch(SQLException se){
		}
	}
		/*checkLa.setText("SELECT Checkbox: " +   
				(e.getStateChange() == 1 ? "checked" : "unchecked"));
			}
		});  if(q1cb1.getState())  if(e.getStateChange() == ItemEvent.SELECTED){ 

		selectchk.addItemListener(new ItemListener() {
	    @Override
		public void itemStateChanged(ItemEvent e) {
		}
		});
if(e.getState()){
			 }
*/

	void Actions(){

		insertB.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e){
			  String rtname = tCombo.getSelectedItem().toString();
			  vecCulName.clear();
			  tableMetaData.clear();
			  insertBec();
			  tableData(rtname); //++
			  model.setDataVector(tableMetaData,vecCulName); 
			  addTextField();
	          cp.revalidate(); // ��Ȯ���ؼ�
              cp.repaint();   
			}
		});

		fieldB.addActionListener(new ActionListener (){ // ���� 
			@Override
			public void actionPerformed(ActionEvent e){
				vecCulName.clear();
				tableMetaData.clear();
				textArray1.clear();
				tCombo.setEnabled(true);
				String rtname = tCombo.getSelectedItem().toString();
				tableData(rtname); //++ ��������.
				selectColumName(rtname);
				t1Combo.setSelectedIndex(0);
				tCombo.requestFocus();
				model.setDataVector(tableMetaData,vecCulName);
				addTextField();
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
				//JTable target = (JTable) me.getSource();jtable1
				row = jtable1.getSelectedRow();
				col = jtable1.getSelectedColumn();
				addTextField();
				int ssc = jtable1.getColumnCount();
					try{
						for(int i=0;i<ssc;i++){
							Object getValue = jtable1.getValueAt(row,i); // String getValue = jtable1.getValueAt(row, i).toString(); ���� �߸����� 		datee.indexOf(":")substring(datee)
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
				jtable1.revalidate(); // ��Ȯ���ؼ�
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
	
		exitB.addActionListener(new ActionListener (){  // �� ������ �ݱ� 
			@Override
			public void actionPerformed(ActionEvent e){
				    viewdropp();
					closeAll();
					System.exit(0);
			 }	
		});	
        
		
		
		//if(selectchk.getStateChange()){}
		
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
				if(selectchk.isSelected()){ // checkbox �� ��쿡�� getState()
					vecCulName.clear();
					tableMetaData.clear();
					textArray1.clear();
					int kCode=e.getKeyChar();
					//String kText = e.getKeyText(kCode);
					String ss1 = Character.toString(kCode);
					String kk12 = textField2.getText()+ss1;
					if(e.paramString().indexOf("Backspace") != -1){
					kk12 = (textField2.getText()+ss1).substring(0 , kk12.length()-1);
					}
					viewfieldSelect(kk12);  // Ű�̺�Ʈ �޾Ƽ� ���̺����
					model.setDataVector(tableMetaData,vecCulName);
					jtable1.revalidate(); // ��Ȯ���ؼ�
				    jtable1.repaint();
					addTextField();
					cp.revalidate(); 
					cp.repaint();
					textField2.requestFocus();
				}
				else{
					vecCulName.clear();
					tableMetaData.clear();
					textArray1.clear();
					int kCode=e.getKeyChar();
					//String kText = e.getKeyText(kCode);
					String ss1 = Character.toString(kCode);
					String kk12 = textField2.getText()+ss1;
					String rtname1 = tCombo.getSelectedItem().toString();
					if(e.paramString().indexOf("Backspace") != -1){
					kk12 = (textField2.getText()+ss1).substring(0 , kk12.length()-1);
					}
					fieldSelect(rtname1 ,kk12);  // Ű�̺�Ʈ �޾Ƽ� ���̺����
					model.setDataVector(tableMetaData,vecCulName);
					addTextField();
					jtable1.revalidate(); // ��Ȯ���ؼ�
				    jtable1.repaint();
					cp.revalidate(); 
					cp.repaint();
					textField2.requestFocus();
				}
			}
		});
		viewB.addActionListener(new ActionListener (){
				@Override
				public void actionPerformed(ActionEvent e){
					if(selectchk.isSelected()){ // checkbox �� ��쿡�� getState()
					   viewdropp();
					   vecCulName.clear();
					   tableMetaData.clear();
					   textArray1.clear();
					   selectBec();
					   viewcreate(true);
					   tCombo.setEnabled(false);
					   //tCombo.setSelectedIndex(0);
					   //tableData(rtname1); 
					   model.setDataVector(tableMetaData,vecCulName);  
					   addTextField();
					   jtable1.revalidate(); // ��Ȯ���ؼ�
				       jtable1.repaint();
					   cp.revalidate(); 
					   cp.repaint();
					   textField3.requestFocus();
					}

					if(insertchk.isSelected()){ 
					   vecCulName.clear();
					   tableMetaData.clear();
					   textArray1.clear();	
					   selectBec();
					   //tCombo.setSelectedIndex(0);
					   //tableData(rtname1); 
					   model.setDataVector(tableMetaData,vecCulName);  
					   addTextField();
					   jtable1.revalidate(); // ��Ȯ���ؼ�
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
					   jtable1.revalidate(); // ��Ȯ���ؼ�
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
					   //tCombo.setSelectedIndex(0);
					   //tableData(rtname1); 
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
					   int Rs = JOptionPane.showConfirmDialog(null, "���̺��� ������ �����Ͻ� �ǰ���?", "DDL Ȯ��", JOptionPane.YES_NO_OPTION);
					   if(Rs == JOptionPane.YES_OPTION){	
								vecCulName.clear();
								tableMetaData.clear();
								textArray1.clear();
								ddlTable();
								//int s = tCombo.getIndexOf(tCombo.length());
								//tCombo.setSelectedIndex(s); 
								userTableName();
								//tableData(rtname1);
								model.setDataVector(tableMetaData,vecCulName);  
								addTextField();
								cp.revalidate(); 
								cp.repaint();
								textField3.requestFocus();
								JOptionPane.showMessageDialog(null, "���̺� ���� ó�� �Ϸ�. ��˻��ϼ���.", "�����Ϸ�", JOptionPane.OK_OPTION);
						}else if(Rs == JOptionPane.NO_OPTION){
							    return;
						}
					}
					if(dropchk.isSelected()){ 
					   vecCulName.clear();
					   tableMetaData.clear();
					   textArray1.clear();
					   int Rs = JOptionPane.showConfirmDialog(null, "���̺��� ������ �����Ͻ� �ǰ���?", "DDL Ȯ��", JOptionPane.YES_NO_OPTION);
					   if(Rs == JOptionPane.YES_OPTION){	
								vecCulName.clear();
								tableMetaData.clear();
								textArray1.clear();
								ddlTable();
								//tCombo.setSelectedIndex(s); 
								userTableName();
								//tableData(rtname1);
								model.setDataVector(tableMetaData,vecCulName);  
								addTextField();
								cp.revalidate(); 
								cp.repaint();
								textField3.requestFocus();
								JOptionPane.showMessageDialog(null, "���̺� ���� ó�� �Ϸ�. ��˻��ϼ���.", "�����Ϸ�", JOptionPane.OK_OPTION);
						}else if(Rs == JOptionPane.NO_OPTION){
							    return;
						}
					}

					if(alterchk.isSelected()){ 
					   vecCulName.clear();
					   tableMetaData.clear();
					   textArray1.clear();
					   int Rs = JOptionPane.showConfirmDialog(null, "���̺��� ������ �����Ͻ� �ǰ���?", "DDL Ȯ��", JOptionPane.YES_NO_OPTION);
					   if(Rs == JOptionPane.YES_OPTION){	
								vecCulName.clear();
								tableMetaData.clear();
								textArray1.clear();
								ddlTable();
								//tCombo.setSelectedIndex(); 
								userTableName();
								//tableData(rtname1);
								model.setDataVector(tableMetaData,vecCulName);  
								addTextField();
								cp.revalidate(); 
								cp.repaint();
								textField3.requestFocus();
								JOptionPane.showMessageDialog(null, "���̺� ���� ó�� �Ϸ�. ��˻��ϼ���.", "�����Ϸ�", JOptionPane.OK_OPTION);
						}else if(Rs == JOptionPane.NO_OPTION){
							    return;
						}
					}

					if(selectdefaultchk.isSelected()){
					   String rtname1 = tCombo.getSelectedItem().toString();
					   vecCulName.clear();
					   tableMetaData.clear();
					   textArray1.clear();
					   // selectBec();
					   tCombo.setEnabled(true);
					   //tCombo.setSelectedIndex(0);
					   tableData(rtname1); 
					   model.setDataVector(tableMetaData,vecCulName);  
					   addTextField();
					   cp.revalidate(); 
					   cp.repaint();
					   textField3.requestFocus();
				}
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
					   pln(sql+"");
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
						//selectBec();
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
	public static void main(String[] args) 
	{	
		JAE1 jae = new JAE1();
		//jae.userTableName();	
	}
}

  //combo.addItem(text) // �޺��ڽ��� �߰��ϱ� addItem(); getText();
/*	
tCombo.addActionListener(new ActionListener (){
		public void actionPerformed(ActionEvent e){
		   Object obj = e.getSourse(); 
		   if( obj == tCombo){
				JComboBox jj = (JComboBox)e.getSourse();	
		        String see = jj
		   }
		}
	});
*/

/*
tCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				if( ie.getStateChange() == ItemEvent.SELECTED ) {
					if( tCombo.getSelectedIndex() != 0 ) {
					jp2.removeAll();
					}
				}
			}
		});
*/

// int rowCount = rsmd.getPrecision(1); �������� ũ�����..	

/*
 SELECT column_name FROM all_cons_columns WHERE constraint_name = (
  SELECT constraint_name FROM all_constraints 
  WHERE UPPER(table_name) = UPPER('ACC') AND CONSTRAINT_TYPE = 'P'
);



SELECT a.COLUMN_NAME , u.INDEX_NAME  FROM all_cons_columns a , user_constraints u  WHERE a.constraint_name = (
  SELECT constraint_name FROM all_constraints 
  WHERE UPPER(table_name) = UPPER('ACC') AND CONSTRAINT_TYPE = 'P'
) and a.CONSTRAINT_NAME = u.CONSTRAINT_NAME;

CONSTRAINT_NAME

*/

	/*
	String table1 = "EMP";
	String id1 = "scott";
	void tableData1(){
		String getTableSQL ="select * from EMP";
		try{
		ResultSet rs = db.getPrimaryKeys(null,id1,table1);

		pln("table :"+ rs);
		
		}catch(SQLException se){}
		
	}*/ // �����⸸���� �����常 ������.


/*
		for(int kk=0;kk<cc1;kk++){
			boolean textb = textArray1.get(kk).getText().equals("");
			if(textb==true){
				int kk1 = 0;
				kk1++;
				if(kk1 == cc1){ 
					System.exit(0);
				}
			}
			if(textb==false){
				//���� ������ �ʿ䰡��������..
			}
		}

					for(int i=0;i<cc1;i++){
            Object getValue1 = jtable1.getValueAt(i,selectpPK);
			String getvalues = ((String)getValue1);
			String ff = textArray1.get(selectpPK).getText();
			pln(""+ff+getvalues);
				if(ff.equals(getvalues)){
					JOptionPane.showMessageDialog(null, "�ߺ� Ű ", "�⺻ Ű �ߺ� �Ǵ� ����������", JOptionPane.OK_OPTION);
				}else{
					JOptionPane.showMessageDialog(null, "���������� ", "�⺻ Ű �ߺ� �Ǵ� ����������", JOptionPane.OK_OPTION);
				}

			}
		*/

			
				/*
		if(rw==0){
			for(int i=0; i<cc; i++){
				textArray1.add(new JTextField());
				jp2.add(textArray1.get(i));
				textArray1.get(i).setText("");
				}	
		}else if(rw>=1){
			for(int i2=0; i2<cc; i2++){
				textArray1.add(new JTextField());
				jp2.add(textArray1.get(i2));
				textArray1.get(i2).setText("");		
					if(selectC==i2){
						 textArray1.get(i2).setEnabled(false);
						 k33 = i2;
						 jp2.add(textArray1.get(i2));
					}
			}
		}*/



			/*
    void updateBec(){
		String CCtname = tCombo.getSelectedItem().toString();
		String CpK = selectPK(CCtname);
		// �÷��� ->  textArray1 �� ��ȣ�� �´� �÷��ε��� �����ͼ� �÷��� �̰�  �� �÷��� �´� �� �����ϰ� 
		int cc1 = jtable1.getColumnCount();
		int s = selectPKindex();
		String sql1 = "update "+CCtname+" set " +�÷���+ " = '"+ �� +"' "   //where "+CpK+" = '"+textArray1.get(s).getText()+"'";
		try{ //1
			boolean selectpPKbool = textArray1.get(selectpPK).getText().equals("");
			boolean textb = textArray1.get(0).getText().equals("");
			int rw1 = jtable1.getRowCount();
				
				for(int i2=0; i2<cc; i2++){
						=textArray1.get(i2)
			
			if(selectpPKbool==true){
					JOptionPane.showMessageDialog(null, "Ű ���� ���� UDATE�� ���еǾ����ϴ�.", "�⺻ Ű �Ǵ� �� �Ҹ�", JOptionPane.OK_OPTION);
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
			
			int selectC = selectPKindex();
		
			for(int i2=0; i2<cc; i2++){
				textArray1.add(new JTextField());
				jp2.add(textArray1.get(i2));
				textArray1.get(i2).setText("");		
					if(selectC==i2){
						 textArray1.get(i2).setEnabled(false);
						 jp2.add(textArray1.get(i2));
					}
			}*/
			/*st.executeUpdate(sql1);	
		}catch(SQLException se){
          JOptionPane.showMessageDialog(null, "�ߺ� Ű �Ǵ� ����������(Ÿ��) ���� (DATE : YYYYMMDD �������� �Է��ϼ���.)", "�⺻ Ű �ߺ� �Ǵ� ����������", JOptionPane.OK_OPTION);
		}
		textField2.setText("");
		textArray1.clear();
	}	
*/

/*
	selectExecute.addActionListener(new ActionListener (){
		public void actionPerformed(ActionEvent e){
			vecCulName.clear();
            tableMetaData.clear();
			textArray1.clear();
			selectBec();
			t1Combo.setSelectedIndex(0);  
			model.setDataVector(tableMetaData,vecCulName);  
			addTextField();
			cp.revalidate(); 
            cp.repaint(); 
		 }
	
	});
*/

/*
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

*/
		/*
		selectExecute = new JButton(" DDL , DQL EXECUTE ");
        selectExecute.setHorizontalTextPosition(SwingConstants.CENTER);
        selectExecute.setPreferredSize(new Dimension(150, 30));
        selectExecute.setFocusPainted(false);
        selectExecute.setFont(new Font("�����ٸ����", Font.BOLD, 16));
        selectExecute.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectExecute.setForeground(Color.WHITE);
        selectExecute.setBackground(Color.ORANGE);
        selectExecute.setBorder(null);
        panel_Btn.add(selectExecute);
        //jp1.add(BColumnFieldUpdate);
        //insertB.addActionListener(this);
			*/