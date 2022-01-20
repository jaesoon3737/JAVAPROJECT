import java.awt.*;
import javax.swing.*;

public class Cell extends JButton   //JButton ��ӹ޾Ƽ� cell ó��
{
   private char type;      // e = empty,n = number,m = mine,f = flag
   private int value;      //
   private boolean clicked;   //���� Ŭ�� ���� �� Ȯ���Ϸ���
   private char prevtype;      // �÷��׸� ���� ��츦 ����Ͽ� ���� ������ �����

   public Cell()
   {
      super("");
      this.clicked = false;
      this.type = 'e';
      this.value = 0;
   }

   public void setValue(int n)
   {
      this.value = n;
   }

   public int getValue(){
      return this.value;
   }

   public char getType(){
      return this.type;
   }

   public void setType(char type){
      this.type = type;
   }

   public char getPrevtype(){
      return this.prevtype;
   }

   public void setPrevtype(char type){
      this.prevtype = type;
   }

   public boolean isAMine(){
      if(this.type =='m'){
         return true;
      }
      return false;
   }

   public boolean isClicked(){
      return this.clicked;
   }
    
   public void setClicked(boolean b){
      this.clicked = b;
   }
}