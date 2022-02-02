package ClientTest.domain;

public class ClientMembers {
		private int number;
		private String id;
		private String password;
		private String passwordChk;
		private String name;
		private String nickName;
		private String email;
		private String power;
		
		public ClientMembers() {
			super();
		}
		public ClientMembers(int number, String id, String password, String passwordChk, String name, String nickName,
				String email, String power) {
			super();
			this.number = number;
			this.id = id;
			this.password = password;
			this.passwordChk = passwordChk;
			this.name = name;
			this.nickName = nickName;
			this.email = email;
			this.power = power;
		}

		public int getNumber() {
			return number;
		}

		public void setNumber(int number) {
			this.number = number;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getPasswordChk() {
			return passwordChk;
		}

		public void setPasswordChk(String passwordChk) {
			this.passwordChk = passwordChk;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getNickName() {
			return nickName;
		}

		public void setNickName(String nickName) {
			this.nickName = nickName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPower() {
			return power;
		}

		public void setPower(String power) {
			this.power = power;
		}
		
		
		
}
