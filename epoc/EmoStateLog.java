package com.emotiv.epoc;

import com.emotiv.epoc.EmoState.EE_ExpressivAlgo_t;//////////？
import com.emotiv.ui.Gui;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.*;


//import comm.ContinueRead;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import javax.swing.*;
import java.awt.*;

/**
 * Simple example of JNA interface mapping and usage.
 */
public class EmoStateLog extends JFrame implements ActionListener
{
	static boolean GP=true;
	MyPanel mb=null;
	GuankaPanel gkp=null;
	JMenu cd1=null;
	JMenuBar cdl=null;
	JMenuItem cdx=null;
    public static void main(String[] args) 
    {
    	ContinueRead cRead = new ContinueRead();
        int i = cRead.startComPort();
        if (i == 1)
        {
            cRead.start();
        }
    	EmoStateLog bb=new EmoStateLog();
        boolean COMPOSER = false;
        boolean EPOC = true;
        /**
        //double[] buffer = new double[23];
         **/
        //////////// MODE
        boolean mode = COMPOSER;
        Pointer eEvent = Edk.INSTANCE.EE_EmoEngineEventCreate();
        Pointer eState = Edk.INSTANCE.EE_EmoStateCreate();
        IntByReference userID = null;
        short composerPort = (mode ? (short) 3008 : (short) 1726);
        int option = (mode ? 2 : 1);
        int state = 0;
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (Exception e) {
            System.out.println("Robot failure");
        }
        userID = new IntByReference(0);

        switch (option) {
            case 1: {
                if (Edk.INSTANCE.EE_EngineConnect("Emotiv Systems-5") != EdkErrorCode.EDK_OK.ToInt()) {
                    System.out.println("Emotiv Engine start up failed.");
                    return;
                }
                break;
            }
            case 2: {
                System.out.println("Target IP of EmoComposer: [127.0.0.1] ");

                if (Edk.INSTANCE.EE_EngineRemoteConnect("127.0.0.1", composerPort, "Emotiv Systems-5") != EdkErrorCode.EDK_OK.ToInt()) {
                    System.out.println("Cannot connect to EmoComposer on [127.0.0.1]");
                    return;
                }
                System.out.println("Connected to EmoComposer on [127.0.0.1]");
                break;
            }
            default:
                System.out.println("Invalid option...");
                return;
        }
//        System.out.println(GP);
//        while(EmoStateLog.GP){
//        	if(GP==false){
//        		break;
//        	}
//        }
        while (true) {
//        	System.out.println(1);
            state = Edk.INSTANCE.EE_EngineGetNextEvent(eEvent);
            // 输出各通道的信号值
//            System.out.println(Edk.INSTANCE.EE_DataGet(eState, 0, buffer, 10));buffer
//            Edk.INSTANCE.EE_DataGet(hData, channel, buffer, bufferSizeInSample);//////////////得到通道的信号
            //New event needs to be handled
            if (state == EdkErrorCode.EDK_OK.ToInt()) {
                int eventType = Edk.INSTANCE.EE_EmoEngineEventGetType(eEvent);
                Edk.INSTANCE.EE_EmoEngineEventGetUserId(eEvent, userID);

                // Log the com.emotiv.epoc.EmoState if it has been updated
                if (eventType == Edk.EE_Event_t.EE_EmoStateUpdated.ToInt()) {
                    Edk.INSTANCE.EE_EmoEngineEventGetEmoState(eEvent, eState);
//                	EE_ExpressivAlgo_t upperFaceType = ES_ExpressivGetUpperFaceAction(eState);
                    float timestamp = EmoState.INSTANCE.ES_GetTimeFromStart(eState);
                    //System.out.println(timestamp + " : New com.emotiv.epoc.EmoState from user " + userID.getValue());
                    //System.out.print("WirelessSignalStatus: ");
                    //System.out.println(com.emotiv.epoc.EmoState.INSTANCE.ES_GetWirelessSignalStatus(eState));

                    int action = EmoState.INSTANCE.ES_CognitivGetCurrentAction(eState);
                    double power = EmoState.INSTANCE.ES_CognitivGetCurrentActionPower(eState);
                    if (power != 0) {
                        if (action == EmoState.EE_CognitivAction_t.COG_LEFT.ToInt()) {
                            //System.out.println("Left. Power: " + power);
                            robot.keyPress(KeyEvent.VK_UP);
                            robot.keyRelease(KeyEvent.VK_UP);
                        }
                        if (action == EmoState.EE_CognitivAction_t.COG_RIGHT.ToInt()) {
                            //System.out.println("Right. Power: " + power);
                            robot.keyPress(KeyEvent.VK_DOWN);
                            robot.keyRelease(KeyEvent.VK_DOWN);
                        }
                    }

                    if (EmoState.INSTANCE.ES_ExpressivGetSmileExtent(eState) == 1){
//                       gui.mp.setBackground(Color.WHITE);
                    //System.out.println("Blink");
                    	  try {
                              char c='1';
                               cRead.outputStream.write(c);
                          } catch (IOException e) {
                              e.printStackTrace();
                          }
                    	  if(bb.mb.mt.y<1100){
                    	 bb.mb.mt.y+=10;
                    	 bb.mb.mt.setFangxiang(2);
                    	  }
                    	 }
                    if(EmoState.INSTANCE.ES_ExpressivGetClenchExtent(eState)==1){
//                    	bb.mb.setBackground(Color.green);
                    	if(bb.mb.mt.shengming){
                    	bb.mb.mt.shot();
                    	InputStream inputt=bb.getClass().getResourceAsStream("/music/Shotjiequ.mp3");
    					Shengyin syy=new Shengyin(inputt);
    					syy.start();
                    	}
//                    	System.out.println("发射");
                    }
                    if (EmoState.INSTANCE.ES_ExpressivGetEyebrowExtent(eState) == 1) {
                        //System.out.println("LeftWink");
//                        gui.mp.setBackground(Color.WHITE);
//                        robot.keyPress(KeyEvent.VK_LEFT);
//                        robot.keyRelease(KeyEvent.VK_LEFT);
                    	  try {
                              char b='4';
                               cRead.outputStream.write(b);
                          } catch (IOException e) {
                              e.printStackTrace();
                          }
                    	  if(bb.mb.mt.y>0){
                    	 bb.mb.mt.y-=10;
                    	 bb.mb.mt.setFangxiang(0);
                    	  }
                    }
//                    if (EmoState.INSTANCE.ES_ExpressivIsRightWink(eState) == 1) {
//                        //System.out.println("RightWink");
//                        gui.mp.setBackground(Color.WHITE);
//                        robot.keyPress(KeyEvent.VK_RIGHT);
//                        robot.keyRelease(KeyEvent.VK_RIGHT);
//                    }
                    if (EmoState.INSTANCE.ES_ExpressivIsLookingLeft(eState) == 1) {
                        System.out.println("左腿抬起...");
//                        gui.mp.setBackground(Color.BLUE);
//                        if (i == 1)
//                        {
//                            cRead.start();
                            try {
                                char a='2';
                                 cRead.outputStream.write(a);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
//                        } 
//                        else {
//                            return;
//                        }
                            if(bb.mb.mt.x>0){
                        bb.mb.mt.x-=10;
                        bb.mb.mt.setFangxiang(1);
                            }
                    }

                    if (EmoState.INSTANCE.ES_ExpressivIsLookingRight(eState) == 1) {
                        System.out.println("右腿抬起...");
                        try {
                            char d='3';
                             cRead.outputStream.write(d);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if(bb.mb.mt.x<1400){
                       bb.mb.mt.x+=10;
                       bb.mb.mt.setFangxiang(3);
                        }
                    }
                    bb.repaint();
                    //System.out.print("ExcitementShortTerm: ");
                    //System.out.println(com.emotiv.epoc.EmoState.INSTANCE.ES_AffectivGetExcitementShortTermScore(eState));
                    //System.out.print("ExcitementLongTerm: ");
                    //System.out.println(com.emotiv.epoc.EmoState.INSTANCE.ES_AffectivGetExcitementLongTermScore(eState));
                    //System.out.print("EngagementBoredom: ");
                    //System.out.println(com.emotiv.epoc.EmoState.INSTANCE.ES_AffectivGetEngagementBoredomScore(eState));

                    //System.out.print("CognitivGetCurrentAction: ");
                    //System.out.println(com.emotiv.epoc.EmoState.INSTANCE.ES_CognitivGetCurrentAction(eState));
                    //System.out.print("CurrentActionPower: ");
                    //System.out.println(com.emotiv.epoc.EmoState.INSTANCE.ES_CognitivGetCurrentActionPower(eState));
                }
            } else if (state != EdkErrorCode.EDK_NO_EVENT.ToInt()) {
                System.out.println("Internal error in Emotiv Engine!");
                break;
            }
        }

        Edk.INSTANCE.EE_EngineDisconnect();
        System.out.println("Disconnected!");
        
    }
    public EmoStateLog()
    {
    	cd1=new JMenu("新游戏");
		cd1.setMnemonic('G');
		cdl=new JMenuBar();
		cdx=new JMenuItem("新游戏(N)");
		cdx.addActionListener(this);
		cdx.setActionCommand("new game");
		cd1.add(cdx);
		cdl.add(cd1);
		this.setJMenuBar(cdl);
//		this.setIconImage((new ImageIcon("timg.jpg")).getImage());
		
     
        
		gkp=new GuankaPanel();
		this.add(gkp);
//		Thread gkpxc=new Thread(gkp);
//		gkpxc.start();
		this.setSize(600,500);
		this.setLocation(400,350);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
    }
    
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("new game")){
				EmoStateLog.GP=false;
				mb=new MyPanel();
				mb.setLayout(null);
				this.remove(gkp);
				this.add(mb);
//				this.addKeyListener(mb);
				Thread t=new Thread(mb);
				t.start();
				
				this.setVisible(true);
				
//				System.out.println(EmoStateLog.GP);
			}
		}
}
    class GuankaPanel extends JPanel {
    	public void paint(Graphics g){
    		super.paint(g);
    		g.setColor(Color.white);
    		g.fillRect(0, 0, 1200, 800);
    		g.setColor(Color.black);
    		Font font =new Font("华文行楷",Font.BOLD,38);
    		g.setFont(font);
    		g.drawString("准备好测试你的脑电了吗?", 300, 100);
    		g.drawString("准备好了就开始吧！", 350, 150);
    		Toolkit tool=this.getToolkit();
    		Image image=tool.getImage("/timg.jpg");
    		g.drawImage(image, 420, 160, 200, 200, this);
    		g.drawString("【操作指南】", 400, 400);
    		g.drawString("抬眉向上    左看向左    右看向右    ",250,450);
    		g.drawString("微笑向下    咬牙发射", 340, 500);
    		g.drawString("吉林大学陈老师课题组倾情制作，请准备好你的五官~~", 300, 750);
    	   }
    			
    }
    class MyPanel extends JPanel implements Runnable,KeyListener
    {
    	boolean life=true;
    	static int a,b;
    	JButton start,pause,stop;
        JLabel hour,min,sec,ms;
        Start ss;
    	MyTank mt=null;
    	Xiaochu xiaochu=new Xiaochu();
    	Vector<DiTank> dtk=new Vector<DiTank>();//这个东西声明一次就够了 要么在子类中声明，要么在需要的地方声明（最好是面板）
    	Vector<Baozha> bzjh=new Vector<Baozha>();
    	
    	int tksl=3;
    	Image tp1=null;
    	Image tp2=null;
    	Image tp3=null;
    	public MyPanel()
    	{
    		mt=new MyTank(750,1000);
//    		try{
//    			tp1=ImageIO.read(new File("bzxg1.gif"));
//    			tp2=ImageIO.read(new File("bzxg2.gif"));
//    			tp3=ImageIO.read(new File("bzxg3.gif"));
//    		}catch(Exception e){}
    		tp1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bzxg1.gif"));
    		tp2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bzxg2.gif"));
    		tp3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bzxg3.gif"));
    		for(int i=0;i<3;i++)
    		{
    			DiTank dt=new DiTank(i*500+5,0);
    			dt.setFangxiang(2);
    			dt.dtkxl(dtk);
    			Thread t=new Thread(dt);
    			t.start();
//    			Zidan zd=new Zidan(dt.x+10,dt.y+30,2);
//    			dt.dzd.add(zd);
//    			Thread t3=new Thread(zd);
//    			t3.start();
    			dtk.add(dt);
    			xiaochu.dtkx(dtk);
    			Thread t1=new Thread(xiaochu);
    			t1.start();
    		}
    		InputStream input=getClass().getResourceAsStream("/music/tankk.mp3");
    		Shengyin sy=new Shengyin(input);
    		sy.start();
    		hour=new JLabel("00");
            hour.setBounds(1580, 70, 80, 50);
            hour.setFont(new Font("Consolas", Font.BOLD, 50));
            hour.setHorizontalAlignment(JLabel.CENTER);

            /**********时钟分钟件冒号**********/
            JLabel m_1=new JLabel(":");
            m_1.setBounds(1640, 70, 30, 50);
            m_1.setFont(new Font("Consolas", Font.BOLD, 50));

            /**********分钟**********/
            min=new JLabel("00");
            min.setBounds(1650, 70, 80, 50);
            min.setFont(new Font("Consolas", Font.BOLD, 50));
            min.setHorizontalAlignment(JLabel.CENTER);

            /**********分钟和秒钟间的冒号**********/
            JLabel m_2=new JLabel(":");
            m_2.setBounds(1710, 70, 30, 50);
            m_2.setFont(new Font("Consolas", Font.BOLD, 50));

            /**********秒钟**********/
            sec=new JLabel("00");
            sec.setBounds(1720, 70, 80, 50);
            sec.setFont(new Font("Consolas", Font.BOLD, 50));
            sec.setHorizontalAlignment(JLabel.CENTER);
            /***********冒号****************/
            JLabel m_3=new JLabel(":");
            m_3.setBounds(1785, 70, 30, 50);
            m_3.setFont(new Font("Consolas", Font.BOLD, 50));
            /***********毫秒****************/
            ms=new JLabel("000");
            ms.setBounds(1795, 70, 120, 50);
            ms.setFont(new Font("Consolas", Font.BOLD, 50));
            ms.setHorizontalAlignment(JLabel.CENTER);
    		  this.add(hour);
    	       this.add(m_1);
    	        this.add(min);
    	        this.add(m_2);
    	        this.add(sec);
    	        this.add(m_3);
    	        this.add(ms);
    	        
//    	    	if(dtk.size()==0){
//    				new Pause(ss).start();
//    				}
    				this.repaint();
    	}
    	public void tjsj(Graphics g){
//    		this.drawTank(80, 600, g, 0, 1);
    		g.setColor(Color.black);
    		g.drawString(Jzjl.getDtsl()+"", 86, 600);
//    		this.drawTank(300, 600, g, 0, 0);
    		g.setColor(Color.black);
    		g.drawString(Jzjl.getMtsl()+"", 306, 600);
    	}
    	public void paint(Graphics g)
    	{
    		super.paint(g);
    		g.drawImage(tp1, 100, 100, 30, 30, this);
    		g.drawImage(tp2, 100, 100, 30, 30, this);
    		g.drawImage(tp3, 100, 100, 30, 30, this);
    		g.fillRect(0, 0, 1500, 1200);
//    		this.drawTank(400, 900, g, 0, 0);
//    		this.tjsj(g);/////////////////////////////////////////////////////////////////////
    		if(mt.shengming)
    		{
    		this.drawTank(mt.getX(), mt.getY(), g, mt.fangxiang, 0);
    		}
    		else
    		{
    			g.setColor(Color.red);
    			Font font =new Font("Consolas",Font.BOLD,100);
    			g.setFont(font);
    			g.drawString("Game Over", 600, 550);
    			
    			new Pause(ss).start();
    		}
    		if(dtk.size()==0){
    			g.setColor(Color.red);
    			Font font =new Font("Consolas",Font.BOLD,100);
    			g.setFont(font);
    			g.drawString("Congratulations", 600, 550);
    		}
    		
    		for(int i=0;i<dtk.size();i++)
    		{
    			DiTank dt=dtk.get(i);
    			if(dt.shengming)
    			{
    			this.drawTank(dt.getX(), dt.getY(), g, dt.fangxiang, 1);
    			for(int j=0;j<dt.dzd.size();j++)
    			{
    				Zidan dtzd=dt.dzd.get(j);
    				if(dtzd.pp)
    				{
    					g.setColor(Color.white);
    					g.fill3DRect(dtzd.x,dtzd.y,5,5,false);
    				}else{
    					
    					dt.dzd.remove(dtzd);
    				}
    			}
    			}
    		}
    		for(int i=0;i<mt.aa.size();i++)
    		{
    			Zidan zidan=mt.aa.get(i);//这里获取zidan对象后不必用mt.去获取绘画中的子弹了
    		if(zidan!=null && zidan.pp==true)
    		{
    			g.setColor(Color.green);
    			g.fill3DRect(zidan.x, zidan.y, 5,5, false);
    		}
    		if(zidan.pp==false)
    		{
    			mt.aa.remove(zidan);
    		}
    		}
    		for(int i=0;i<bzjh.size();i++)
    		{
    			Baozha bz=bzjh.get(i);
    			if(bz.shengcunqi>6)
    			{
    				g.drawImage(tp1, bz.x, bz.y, 80,80,this);
    			}
    			else if(bz.shengcunqi>3)
    			{
    				g.drawImage(tp2, bz.x, bz.y, 80, 80, this);
    			}else{
    				g.drawImage(tp3, bz.x, bz.y, 80, 80, this);
    			}
    			bz.suqsd();
    			if(bz.shengcunqi==0)
    			{
    				bzjh.remove(bz);//清除内存
    			}
    		}
    	}
    	public void jzdf1()
    	{
    		for(int i=0;i<mt.aa.size();i++)
    		{
    			Zidan zd=mt.aa.get(i);
    			if(zd.pp)
    			{
    				for(int j=0;j<dtk.size();j++)
    				{
    					DiTank dt=dtk.get(j);
    					if(dt.shengming)
    					{
    						if(this.jzdf(zd, dt)){
    						Jzjl.jzdf();
    						}
//    						Jzjl.sdsl();
    					}
//    					else
//    					{
//    						dtk.remove(j);
//    						Shengyin sy=new Shengyin("C:/Users/龙天叶/Desktop/photo/Boom.wav");
//    						sy.start();
//    					}
    					
    				}
    			}
    			this.repaint();
    		}
    	}
    	public void jzwf()
    	{
    		for(int i=0;i<this.dtk.size();i++)
    		{
    			DiTank dt=dtk.get(i);
    			for(int j=0;j<dt.dzd.size();j++)
    			{
    				Zidan zd=dt.dzd.get(j);
    				if(mt.shengming)
    				{
    					if(this.jzdf(zd,mt)){
    						try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
    						InputStream input=getClass().getResourceAsStream("/music/lose.mp3");
    						Shengyin sy=new Shengyin(input);
    						sy.start();
    					}
    				}
//    				else
//    				{
//    					Shengyin sy=new Shengyin("C:/Users/龙天叶/Desktop/photo/O.wav");
//    					sy.start();
//    				}
    			}
    			this.repaint();		
    		}
    	}
    	public boolean jzdf(Zidan zidan,Tank dt)//传入双方生命
    	{
    		boolean b=false;
    		switch(dt.fangxiang)
    		{
    		case 0:
    		case 2:
    			if(zidan.x>dt.x&&zidan.x<dt.x+70&&zidan.y>dt.y&&zidan.y<dt.y+80)//比较坐标
    			{
    				zidan.pp=false;
    				dt.shengming=false;
    				dtk.remove(dt);
    				b=true;
    				InputStream input=getClass().getResourceAsStream("/music/BOOM.mp3");
    				Shengyin sy=new Shengyin(input);
    				sy.start();
    				if(Jzjl.getDtsl()==1){
    					new Pause(ss).start();
    					InputStream inputt=getClass().getResourceAsStream("/music/vectory.mp3");
    					Shengyin syy=new Shengyin(inputt);
    					syy.start();
    				}
    				Baozha bz=new Baozha(dt.x,dt.y);
    				bzjh.add(bz);
    			}
    			break;
    		case 1:
    		case 3:
    			if(zidan.x>dt.x&&zidan.x<dt.x+80 &&zidan.y>dt.y&&zidan.y<dt.y+70)
    			{
    				zidan.pp=false;
    				dt.shengming=false;
    				dtk.remove(dt);
    				b=true;
    				InputStream input=getClass().getResourceAsStream("/music/BOOM.mp3");
    				Shengyin sy=new Shengyin(input);
    				sy.start();
    				if(Jzjl.getDtsl()==1){
    					new Pause(ss).start();
    					InputStream inputt=getClass().getResourceAsStream("/music/vectory.mp3");
    					Shengyin syy=new Shengyin(inputt);
    					syy.start();
    				}
    				Baozha bz=new Baozha(dt.x,dt.y);
    				bzjh.add(bz);
    			}
    		}
    		return b;
    	}
    	public void drawTank(int x,int y,Graphics g,int fangxiang,int yanse)
    	{
    		switch(yanse)
    		{
    		case 0:
    			g.setColor(Color.green);
    			break;
    		case 1:
    			g.setColor(Color.yellow);
    			break;
    		}
    		switch(fangxiang)
    		{
    		case 0:
    			g.fill3DRect(x, y, 25, 80, false);
    			g.fill3DRect(x+50, y, 25, 80, false);
    			g.fill3DRect(x+10, y+10,50, 50, false);
    			g.fillOval(x+20,y+15 ,30, 30);
//    			g.drawLine(x+20, y+25, x+20, y-17);
    			g.fill3DRect(x+35, y-30, 5, 40, false);
    			break;
    		case 1:
    			g.fill3DRect(x, y, 80, 25,false);
    			g.fill3DRect(x, y+50, 80, 25, false);
    			g.fill3DRect(x+10, y+10, 50, 50, false);
    			g.fillOval(x+15, y+20, 30, 30);
//    			g.drawLine(x+15, y+10, x-3, y+10);
    			g.fill3DRect(x-30, y+35, 40, 5, false);
    			break;
    		case 2:
    			g.fill3DRect(x, y, 25, 80,false);
    			g.fill3DRect(x+50,y , 25, 80,false);
    			g.fill3DRect(x+10,y+10 , 50, 50,false);
    			g.fillOval(x+20, y+15, 30, 30);
//    			g.drawLine(x+10, y+15, x+10, y+33);
    			g.fill3DRect(x+35, y+60, 5, 40, false);
    			break;
    		case 3:
    			g.fill3DRect(x, y, 80, 25,false);
    			g.fill3DRect(x, y+50, 80, 25, false);
    			g.fill3DRect(x+20, y+10, 50, 50, false);
    			g.fillOval(x+25, y+20, 30, 30);
//    			g.drawLine(x+15, y+10, x+33, y+10);
    			g.fill3DRect(x+65, y+35, 40, 5, false);
    			break;
    		}
    		
    	}
    	public void keyTyped(KeyEvent e) {}
    	public void keyPressed(KeyEvent e) {
    		if(e.getKeyCode()==KeyEvent.VK_W)
    		{
    			this.mt.setFangxiang(0);
    			this.mt.xiangshang();
//    			Shengyin sy=new Shengyin("C:/Users/龙天叶/Desktop/photo/TankMove.wav");
//    			sy.start();
    		}
    		else if(e.getKeyCode()==KeyEvent.VK_S)
    		{
    			this.mt.setFangxiang(2);
    			this.mt.xiangxia();
//    			Shengyin sy=new Shengyin("C:/Users/龙天叶/Desktop/photo/TankMove.wav");
//    			sy.start();
    		}
    		else if(e.getKeyCode()==KeyEvent.VK_A)
    		{
    			this.mt.setFangxiang(1);
    			this.mt.xiangzuo();
//    			Shengyin sy=new Shengyin("C:/Users/龙天叶/Desktop/photo/TankMove.wav");
//    			sy.start();
    		}
    		else if(e.getKeyCode()==KeyEvent.VK_D)
    		{
    			this.mt.setFangxiang(3);
    			this.mt.xiangyou();
//    			Shengyin sy=new Shengyin("C:/Users/龙天叶/Desktop/photo/TankMove.wav");
//    			sy.start();
    		}
    		if (e.getKeyCode()==KeyEvent.VK_J)
    		{
    			if(mt.shengming){
    			if(this.mt.aa.size()<5)
    			{
    			this.mt.shot();
//    			Shengyin sy=new Shengyin("C:/Users/龙天叶/Desktop/photo/shott.wav");
//    			sy.start();
    			}
    			}
    		}
    		this.repaint();
    	}
    	public void keyReleased(KeyEvent e) {}
    	public void run() {
    		while(true)
    		{ 
    			try{
    				Thread.sleep(200);
    			}
    			catch (Exception e){}
    			this.jzdf1();	
    			this.jzwf();	
    			if(dtk.size()!=0){
    				if(life){
    				ss=new Start(hour,min,sec,ms);
    				ss.start();
    				}
    				life=false;
    				}
    			else
    			{
    				new Pause(ss).start();
    			}
    		}
    	}
    }
