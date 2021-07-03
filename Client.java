import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;
public class Client implements ActionListener
{
    JPanel panelOne;
    JTextField textFieldOne;
    JButton buttonOne;  
    static JPanel jPanelOne;  
    static JFrame frameOne=new JFrame();
    static Box vertical=Box.createVerticalBox();
    static Socket socket;
    static DataInputStream dataInputStream;
    static DataOutputStream dataOutputStream;
    
    Boolean typing;

Client()
{
frameOne.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
panelOne=new JPanel();
panelOne.setLayout(null);
panelOne.setBackground(new Color(7,94,84));
panelOne.setBounds(0,0,450,70);
frameOne.add(panelOne);


//one

ImageIcon imageIconOne=new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
Image imageOne=imageIconOne.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
ImageIcon imageIconTwo=new ImageIcon(imageOne);
JLabel labelOne=new JLabel(imageIconTwo);
labelOne.setBounds(5,17,30,30);
panelOne.add(labelOne);


//event
labelOne.addMouseListener(new MouseAdapter(){
public void mouseClicked(MouseEvent mouseEvent){
System.exit(0);
}
});



//two

ImageIcon imageIconThree=new ImageIcon(ClassLoader.getSystemResource("icons/2.png"));
Image imageTwo=imageIconThree.getImage().getScaledInstance(60,60,Image.SCALE_DEFAULT);

ImageIcon imageIconFour=new ImageIcon(imageTwo);
JLabel labelTwo=new JLabel(imageIconFour);
labelTwo.setBounds(40,5,60,60);
panelOne.add(labelTwo);





//three

ImageIcon imageIconFive=new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
Image imageThree=imageIconFive.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);

ImageIcon imageIconSix=new ImageIcon(imageThree);

JLabel labelFive=new JLabel(imageIconSix);
labelFive.setBounds(290,20,30,30);
panelOne.add(labelFive);




//four

ImageIcon imageIconSeven=new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
Image imageFour=imageIconSeven.getImage().getScaledInstance(35,30,Image.SCALE_DEFAULT);

ImageIcon imageIconEight=new ImageIcon(imageFour);

JLabel labelSix=new JLabel(imageIconEight);
labelSix.setBounds(350,20,35,30);
panelOne.add(labelSix);





//five

ImageIcon imageIconNine=new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
Image imageFive=imageIconNine.getImage().getScaledInstance(13,25,Image.SCALE_DEFAULT);

ImageIcon imageIconTen=new ImageIcon(imageFive);

JLabel labelSeven=new JLabel(imageIconTen);
labelSeven.setBounds(410,20,13,25);
panelOne.add(labelSeven);




//name

JLabel labelThree=new JLabel("Anshita");
labelThree.setFont(new Font("SAN_SERIF",Font.BOLD,20));
labelThree.setForeground(Color.WHITE);
labelThree.setBounds(110,15,100,18);
panelOne.add(labelThree);


//status

JLabel labelFour=new JLabel("Active now");
labelFour.setFont(new Font("SAN_SERIF",Font.PLAIN,14));
labelFour.setForeground(Color.WHITE);
labelFour.setBounds(110,35,100,20);
panelOne.add(labelFour);


//timer

Timer timer=new Timer(1,new ActionListener(){
public void actionPerformed(ActionEvent actionEvent){
if(!typing){
labelFour.setText("Active Now");
}
}
});
timer.setInitialDelay(2000);

//textArea
jPanelOne=new JPanel();
//jPanelOne.setBounds(5,75,440,570);
jPanelOne.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));

//frameOne.add(jPanelOne);

JScrollPane scrollPane=new JScrollPane(jPanelOne);
scrollPane.setBounds(5,75,440,570);
scrollPane.setBorder(BorderFactory.createEmptyBorder());



ScrollBarUI scrollBarUI=new BasicScrollBarUI()
{
protected JButton createDecreaseButton(int orientation)
{
JButton button=super.createDecreaseButton(orientation);
button.setBackground(new Color(7,94,84));
button.setForeground(Color.WHITE);
this.thumbColor=new Color(7,94,84);
return button;
}

protected JButton createIncreaseButton(int orientation)
{
JButton button=super.createIncreaseButton(orientation);
button.setBackground(new Color(7,94,84));
button.setForeground(Color.WHITE);
this.thumbColor=new Color(7,94,84);
return button;
}

};

scrollPane.getVerticalScrollBar().setUI(scrollBarUI);
frameOne.add(scrollPane);



//textField
textFieldOne=new JTextField();
textFieldOne.setBounds(5,655,310,40);
textFieldOne.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
frameOne.add(textFieldOne);


textFieldOne.addKeyListener(new KeyAdapter(){
public void keyPressed(KeyEvent keyEvent)
{
labelFour.setText("typing....");
timer.stop();
typing=true;
}
public void keyReleased(KeyEvent keyEvent)
{
typing=false;
if(!timer.isRunning()){
timer.start();
}
}
});


//button
buttonOne=new JButton("Send");
buttonOne.setBounds(320,655,123,40);
buttonOne.setBackground(new Color(7,94,84));
buttonOne.setForeground(Color.WHITE);
buttonOne.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
buttonOne.addActionListener(this);
frameOne.add(buttonOne);

frameOne.getContentPane().setBackground(Color.WHITE);
frameOne.setLayout(null);
frameOne.setSize(450,700);
frameOne.setLocation(900,45);
frameOne.setUndecorated(true);
frameOne.setVisible(true);
}
public void actionPerformed(ActionEvent actionEvent)
{
try
{
String out=textFieldOne.getText();
sendTextToFile(out);
JPanel panelTwo=formatLabel(out);
jPanelOne.setLayout(new BorderLayout());
JPanel panelRight=new JPanel(new BorderLayout());
panelRight.add(panelTwo,BorderLayout.LINE_END);
vertical.add(panelRight);
vertical.add(Box.createVerticalStrut(15));
jPanelOne.add(vertical,BorderLayout.PAGE_START);

dataOutputStream.writeUTF(out);
textFieldOne.setText("");
}catch(Exception ee)
{
System.out.println(ee);
}
}
public void sendTextToFile(String message) throws FileNotFoundException
{
try
{
FileWriter fileWriter=new FileWriter("chat.txt");
PrintWriter printWriter=new PrintWriter(new BufferedWriter(fileWriter));
printWriter.println("Anshita :"+message);
}catch(Exception exception)
{
    exception.printStackTrace();
}
}
public static JPanel formatLabel(String out)
{
JPanel panelThree=new JPanel();
panelThree.setLayout(new BoxLayout(panelThree,BoxLayout.Y_AXIS));
 JLabel labelOne = new JLabel("<html><p style = \"width : 150px\">"+out+"</p></html>");
labelOne.setFont(new Font("Tahoma",Font.PLAIN,16));
labelOne.setBackground(new Color(37,211,102));
labelOne.setOpaque(true);
labelOne.setBorder(new EmptyBorder(15,15,15,50));
Calendar calendar=Calendar.getInstance();
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm");
JLabel labelTwo=new JLabel();
labelTwo.setText(simpleDateFormat.format(calendar.getTime()));
panelThree.add(labelOne);
panelThree.add(labelTwo);
return panelThree;
}
public static void main(String args[])
{
new Client().frameOne.setVisible(true);


String messageInput="";
try
{
socket=new Socket("127.0.0.1",7072);
dataInputStream=new DataInputStream(socket.getInputStream());
dataOutputStream=new DataOutputStream(socket.getOutputStream());

while(true)
{
messageInput=dataInputStream.readUTF();
JPanel panelTwo=formatLabel(messageInput);
JPanel panelLeft=new JPanel(new BorderLayout());
panelLeft.add(panelTwo,BorderLayout.LINE_START);
vertical.add(panelLeft);
frameOne.validate();
}

}catch(Exception e)
{
System.out.println(e);
}
}
}