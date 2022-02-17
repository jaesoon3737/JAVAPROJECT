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
        //문제 21, commit 부분 만들 것,
        //문제 22, id 로그인 하는 부분 만들 것
//문제 23 SYSDATE 어케 넣지ㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣ //  해결
// 셀렉해서 - 뷰생성 - 뷰땡겨서 거기서 검색 // 완료
// 망했따 딜리트했는데 검색됨;; 버튼마다 if로 텍스트필드 읽어서 null 이면 셀렉 안되게 하자. // 해결
// 아래 텍스트에서 검색하면 기본키열림 
// 뷰 생성할 때 입력창 띄워서 뷰이름 생성하게 하고 , 하고난뒤에 그거 받아와서 삭제  ,  기본키 위치가 이상함. 기본키 없는애 검색하면, //완료












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
			con = DriverManager.getConnection(url , id , pass); // 기본 값 scott , tiger
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			//db = con.getMetaData(); 전방향만됨 리절트 셋프라이머리키 
		   // prstUserTableName = con.prepareStatement(userTableNamesql);
		}catch(ClassNotFoundException ce){
			pln("ClassNotFoundException");
			ce.printStackTrace();
		}catch(SQLException se){
			pln("SQLException");
			se.printStackTrace();
		}
	 userTableName(); // 테이블 네임 가져오구
	 tableData(tableNamesV.get(0)); // Jtable 만들어주고 
	 init(); // ui
	 addTextField(); // 필드얘가문젠ㄷ
	 Actions();// 리스너
	}

	void setUI(){
	     setTitle("재순이의 디비관리");
		 setBounds(100 , 100 , 1700 , 800);
		 setVisible(true);
		 setResizable(false);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 끄는거
		 setLocationRelativeTo(null); // 창 가운데 띄우자.
		
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
		textField2Label.setOpaque(true); // 투명
		textField2Label.setFont(new Font("나눔바른고딕", Font.BOLD, 17));
		textField2Label.setHorizontalAlignment(SwingConstants.CENTER);
	    textField2Label.setForeground(Color.BLACK);
		textField2Label.setBackground(Color.WHITE);
		//textField2Label.setBorder(new LineBorder(Color.GRAY));
		 
		 
		columnnameLabel = new JLabel(new ImageIcon("image\\columnname.png"));
		columnnameLabel.setOpaque(true);
		columnnameLabel.setFont(new Font("나눔바른고딕", Font.BOLD, 17));
		columnnameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		columnnameLabel.setForeground(Color.BLACK);
		columnnameLabel.setBackground(Color.WHITE);
		//columnnameLabel.setBorder(new LineBorder(Color.GRAY));
		 
		tablenameLabel = new JLabel(new ImageIcon("image\\45.png"));
		tablenameLabel.setOpaque(true);
		tablenameLabel.setFont(new Font("나눔바른고딕", Font.BOLD, 17));
		tablenameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tablenameLabel.setForeground(Color.BLACK);
		tablenameLabel.setBackground(Color.WHITE);
		//tablenameLabel.setBorder(new LineBorder(Color.GRAY));

		selectLabel = new JLabel(new ImageIcon("image\\sql.png"));
		selectLabel.setOpaque(true); 
		selectLabel.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
		selectLabel.setHorizontalAlignment(SwingConstants.CENTER);
		selectLabel.setForeground(Color.BLACK);
		selectLabel.setBackground(Color.WHITE);
		//selectLabel.setBorder(new LineBorder(Color.BLACK)); 
		 
		insertLabel = new JLabel(new ImageIcon("image\\se.png"));
		insertLabel.setOpaque(true);
		insertLabel.setFont(new Font("나눔바른고딕", Font.BOLD, 17));
		insertLabel.setHorizontalAlignment(SwingConstants.CENTER);
		insertLabel.setBackground(Color.WHITE);
	    insertLabel.setForeground(Color.BLACK);
		//insertLabel.setBorder(new LineBorder(Color.BLACK));
		/*
		DdlSelectLabel = new JLabel("2 . 사용하실 SQL을 선택하세요.  ☞");
		DdlSelectLabel.setOpaque(true);
		DdlSelectLabel.setFont(new Font("나눔바른고딕", Font.BOLD, 17));
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
		
		selectdefaultchk = new JCheckBox("기본 검색");
		selectchk = new JCheckBox("SELECT(검색)");
		insertchk = new JCheckBox("INSERT(추가)"); 
        deletechk = new JCheckBox("DELETE(삭제)");
		updatechk = new JCheckBox("UPDATE(변경)");
	    createchk = new JCheckBox("CREATE(생성)"); 
	    dropchk = new JCheckBox("DROP(삭제)"); 
	    alterchk = new JCheckBox("ALTER(변경)"); 

		ButtonGroup checkButG = new ButtonGroup(); // check 하나만ㄱ ㅏ능하게 설정하는거.
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
        fieldB.setFont(new Font("나눔바른고딕", Font.BOLD, 16));
        fieldB.setAlignmentX(Component.CENTER_ALIGNMENT);
        fieldB.setForeground(Color.WHITE);
        fieldB.setBackground(new Color(0x333CCC));
        fieldB.setBorder(null);
        panel_Btn.add(fieldB);
		
		viewB = new JButton("SQL EXECUTE (실행)");  //  view 권한 없으면 에러뜨니까 경고창으로 뷰권한 필요하다고 할것., 
        viewB.setHorizontalTextPosition(SwingConstants.CENTER);
        viewB.setPreferredSize(new Dimension(200, 30));
        viewB.setFocusPainted(false);
        viewB.setFont(new Font("나눔바른고딕", Font.BOLD, 16));
        viewB.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewB.setForeground(Color.WHITE);
        viewB.setBackground(new Color(0x336CCC));
        viewB.setBorder(null);
        panel_Btn.add(viewB);
		/*
		viewBColse = new JButton("CLOSE SELECT IN RE-SEARCH");  //  view 권한 없으면 에러뜨니까 경고창으로 뷰권한 필요하다고 할것., 
        viewBColse.setHorizontalTextPosition(SwingConstants.CENTER);
        viewBColse.setPreferredSize(new Dimension(200, 30));
        viewBColse.setFocusPainted(false);
        viewBColse.setFont(new Font("나눔바른고딕", Font.BOLD, 16));
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
		//new Color(rand.nextInt(0xFFFFFF))
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
         	
	void tableData(String startname){ // 테이블 데이터 모든거 들고오기,
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
			JOptionPane.showMessageDialog(null, " ※ 예외사항 SQLException : "+se+" ", "기본 키 또는 값 불명", JOptionPane.OK_OPTION);
			se.printStackTrace();
		} finally{
			  try{	
			rs.close();
		 }catch(SQLException se){}
		}	
	}

   String selectPK(String select1Tname1){  // PK 컬럼 네임 뽑기
		String selectPK1 = "SELECT column_name FROM all_cons_columns WHERE constraint_name = (SELECT constraint_name FROM all_constraints WHERE UPPER(table_name) = UPPER('"+select1Tname1+"') AND CONSTRAINT_TYPE ='P')";
	    ResultSet rs = null;
			 try{
				 rs = st.executeQuery(selectPK1);
				 //int rowCount = rs.getRow();
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

	void selectColumName(String startname1){  // 업데이트문용 실행하면 vecCulname1 벡터에 들어감.
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
			JOptionPane.showMessageDialog(null, " ※ 예외사항 SQLException : "+se+" ", "기본 키 또는 값 불명", JOptionPane.OK_OPTION);
			se.printStackTrace();
		} finally{
			  try{	
			rs.close();
		 }catch(SQLException se){}
		}        cp.revalidate(); 
        cp.repaint();
		
	}
	
	int selectPKindex(){  // PK 인덱스 구하기 
		String CCtname = tCombo.getSelectedItem().toString();
		String CpK = selectPK(CCtname);
		int cc1 = jtable1.getColumnCount();
		int s1 = 0;	
		for(int w=0;w<cc1;w++){
			String PKindex = jtable1.getColumnName(w);
				if(getCulName1 == null){    // 키인덱스 검색 결과가 널일 때를 생각해줘야함.
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
			int selectpPK = selectPKindex(); // 기본키가 없거나, 텍스트가 전체다 비었을때 거부
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
		selectColumName(CCtname); //vecCulname1 있는거에 들어감
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
				sql1 = sql1 + vecCulName1.get(u)/*컬럼명*/+" = '"+textArray1.get(u).getText()+"' where "+CpK+" = '"+textArray1.get(s).getText()+"'";
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
			JOptionPane.showMessageDialog(null, " ※ 예외사항 SQLException : "+se+" ", "기본 키 또는 값 불명", JOptionPane.OK_OPTION);
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
		String insertsplit4 = insertsplit3[0]; // 테이블명
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
				while(rs.next()){       //인출시퀀스 문제  valueOf()
				     v1 = new Vector<String>();
					 for(int i=1; i<=culCount; i++){
						if(i==indexSele){
						    Metadata = rs.getString(i);
						    Date s = Date.valueOf(Metadata); // 여길 Date 로 넣어야 해결하는데..
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
	/*update emp set ename = '굿잘된다' where empno = 888
		if(k.length()==19 && k.substring(k.length()-9 , k.length()).equals(" 00:00:00")){
							   String k2 = k.substring(0, k.length()-9);
							   java.sql.Date d=  java.sql.Date.valueOf(k2);
	
	*/ 
	void ddlTable(){	
		String createQ = textField3.getText();            // 콤보박스 바로바꾸기.
		String []createAddI = createQ.split(" ");          // 스플릿으로 이름 들고오기
		String createAddIScda = createAddI[0];
		createAddIS = createAddI[2];
		if(createAddIScda.equalsIgnoreCase("create")){
			//boolean s = createAddIS.contains("(");
			if(createAddIS.contains("(")){ // ( 가 존재할때 서브스트링해야하니까  
				int cF = createAddIS.indexOf("(");         // create 문에서 테이블 이름 빼올때 ( 붙어서 오면 짤라야해서 
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
			tCombo.removeItem(upcreateAddIS); //새로 생성했던애는 지워지고 원래있는 애는 안 지워짐 왜안대지 햇더니 소문자 대문자요.. 
			tCombo.removeItem(lowcreateAddIS);
			tCombo.removeItem(createAddIS);
			pln(createAddIScda+"");
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
	
	void fieldSelect(String startname , String sele){ // 테이블 데이터 모든거 들고오기,
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
    Vector<String> views = new Vector<String>(); // 하고나서 벡터 클리어해주기 , 뷰지우기
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
			  pln("얘는 무슨오류일까" + se);
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
			  pln("얘는 무슨오류일까" + se);
		select e.empno , d.dname from emp  e, dept d where d.deptno = e.deptno	}
	
	}*/
		
	void viewfieldSelect(String sele){ // 테이블 데이터 모든거 들고오기,
		
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
	          cp.revalidate(); // 재확인해서
              cp.repaint();   
			}
		});

		fieldB.addActionListener(new ActionListener (){ // 갠춘 
			@Override
			public void actionPerformed(ActionEvent e){
				vecCulName.clear();
				tableMetaData.clear();
				textArray1.clear();
				tCombo.setEnabled(true);
				String rtname = tCombo.getSelectedItem().toString();
				tableData(rtname); //++ 문제없음.
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
							Object getValue = jtable1.getValueAt(row,i); // String getValue = jtable1.getValueAt(row, i).toString(); 널을 잘못읽음 		datee.indexOf(":")substring(datee)
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
				jtable1.revalidate(); // 재확인해서
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
	
		exitB.addActionListener(new ActionListener (){  // 뷰 생성후 닫기 
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
				if(selectchk.isSelected()){ // checkbox 의 경우에는 getState()
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
					viewfieldSelect(kk12);  // 키이벤트 받아서 테이블생성
					model.setDataVector(tableMetaData,vecCulName);
					jtable1.revalidate(); // 재확인해서
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
					fieldSelect(rtname1 ,kk12);  // 키이벤트 받아서 테이블생성
					model.setDataVector(tableMetaData,vecCulName);
					addTextField();
					jtable1.revalidate(); // 재확인해서
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
					if(selectchk.isSelected()){ // checkbox 의 경우에는 getState()
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
					   jtable1.revalidate(); // 재확인해서
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
					   jtable1.revalidate(); // 재확인해서
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
					   jtable1.revalidate(); // 재확인해서
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
					   int Rs = JOptionPane.showConfirmDialog(null, "테이블을 정말로 변경하실 건가요?", "DDL 확인", JOptionPane.YES_NO_OPTION);
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
								//tCombo.setSelectedIndex(s); 
								userTableName();
								//tableData(rtname1);
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
								//tCombo.setSelectedIndex(); 
								userTableName();
								//tableData(rtname1);
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

  //combo.addItem(text) // 콤보박스에 추가하기 addItem(); getText();
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

// int rowCount = rsmd.getPrecision(1); 데이터의 크기더라..	

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
		
	}*/ // 전방향만가능 포워드만 지원함.


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
				//실행 어차피 필요가없을지도..
			}
		}

					for(int i=0;i<cc1;i++){
            Object getValue1 = jtable1.getValueAt(i,selectpPK);
			String getvalues = ((String)getValue1);
			String ff = textArray1.get(selectpPK).getText();
			pln(""+ff+getvalues);
				if(ff.equals(getvalues)){
					JOptionPane.showMessageDialog(null, "중복 키 ", "기본 키 중복 또는 데이터형식", JOptionPane.OK_OPTION);
				}else{
					JOptionPane.showMessageDialog(null, "데이터형식 ", "기본 키 중복 또는 데이터형식", JOptionPane.OK_OPTION);
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
		// 컬럼명 ->  textArray1 의 번호에 맞는 컬럼인덱스 가져와서 컬럼명 뽑고  그 컬럼에 맞는 값 들어가야하고 
		int cc1 = jtable1.getColumnCount();
		int s = selectPKindex();
		String sql1 = "update "+CCtname+" set " +컬럼명+ " = '"+ 값 +"' "   //where "+CpK+" = '"+textArray1.get(s).getText()+"'";
		try{ //1
			boolean selectpPKbool = textArray1.get(selectpPK).getText().equals("");
			boolean textb = textArray1.get(0).getText().equals("");
			int rw1 = jtable1.getRowCount();
				
				for(int i2=0; i2<cc; i2++){
						=textArray1.get(i2)
			
			if(selectpPKbool==true){
					JOptionPane.showMessageDialog(null, "키 값이 없어 UDATE가 실패되었습니다.", "기본 키 또는 값 불명", JOptionPane.OK_OPTION);
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
          JOptionPane.showMessageDialog(null, "중복 키 또는 데이터형식(타입) 오류 (DATE : YYYYMMDD 형식으로 입력하세요.)", "기본 키 중복 또는 데이터형식", JOptionPane.OK_OPTION);
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
        selectExecute.setFont(new Font("나눔바른고딕", Font.BOLD, 16));
        selectExecute.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectExecute.setForeground(Color.WHITE);
        selectExecute.setBackground(Color.ORANGE);
        selectExecute.setBorder(null);
        panel_Btn.add(selectExecute);
        //jp1.add(BColumnFieldUpdate);
        //insertB.addActionListener(this);
			*/