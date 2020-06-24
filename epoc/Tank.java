package com.emotiv.epoc;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.Vector;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JLabel;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

//*子弹动 用线程*/
class Tank
{
	boolean shengming=true;
	int x=0;
	int y=0;
	int fangxiang=0;
	int sudu=6;
	int time=0;
	public Tank(int x, int y) {
		this.x=x;
		this.y=y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getFangxiang() {
		return fangxiang;
	}
	public void setFangxiang(int fangxiang) {
		this.fangxiang = fangxiang;
	}
	public int getSudu() {
		return sudu;
	}
	public void setSudu(int sudu) {
		this.sudu = sudu;
	}
	public void Tank(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
}
class DiTank extends Tank implements Runnable
{
	int sudu=2;
	boolean life=true;
	int fangxiang;
	Vector<Zidan> dzd=new Vector<Zidan>();
	Vector<DiTank> dtk=new Vector<DiTank>();
	public void dtkxl(Vector<DiTank> dxl){
		this.dtk=dxl;
	}
	public DiTank(int x,int y)
	{
		super(x,y);
	}
	public boolean huxiangpengzhuang()
	{
		boolean b=false;
		switch(this.fangxiang)
		{
		case 0:
			
			for(int i=0;i<dtk.size();i++)
			{
				DiTank dt=dtk.get(i);
				
				if(dt!=this)
				{
					if(dt.fangxiang==0||dt.fangxiang==2)
					{
						if(this.x>=dt.x && this.x<=dt.x+80 && this.y>=dt.y && this.y<=dt.y+90)
						{
							return true;
						}
						if(this.x+80>=dt.x && this.x+80<=dt.x+80 && this.y>=dt.y && this.y<=dt.y+90)
						{
							return true;
						}
					}
					if(dt.fangxiang==3||dt.fangxiang==1)
					{
						if(this.x>=dt.x && this.x<=dt.x+90 && this.y>=dt.y && this.y<=dt.y+80)
						{
							return true;
						}
						if(this.x+80>=dt.x && this.x+90<=dt.x+90 && this.y>=dt.y && this.y<=dt.y+80)
						{
							return true;
						}
					}
				}
			}
			break;
		case 1:
			for(int i=0;i<dtk.size();i++)
			{
				DiTank dt=dtk.get(i);
				if(dt!=this)
				{
				    if(dt.fangxiang==0||dt.fangxiang==2)
					{
						if(this.x>=dt.x&&this.x<=dt.x+80&&this.y>=dt.y&&this.y<=dt.y+90)
						{
							return true;
						}
						//下一点
						if(this.x>=dt.x&&this.x<=dt.x+80&&this.y+80>=dt.y&&this.y+80<=dt.y+90)
						{
							return true;
						}
					}
					if(dt.fangxiang==3||dt.fangxiang==1)
					{
						if(this.x>=dt.x&&this.x<=dt.x+90&&this.y>=dt.y&&this.y<=dt.y+80)
						{
							return true;
						}
						if(this.x>=dt.x&&this.x<=dt.x+90&&this.y+80>=dt.y&&this.y+80<=dt.y+80)
						{
							return true;
						}
					}
				}
			}
			break;
		case 2:
			for(int i=0;i<dtk.size();i++)
			{
				DiTank dt=dtk.get(i);
				if(dt!=this)
				{
					if(dt.fangxiang==0||dt.fangxiang==2)
					{
						if(this.x>=dt.x&&this.x<=dt.x+80&&this.y+90>=dt.y&&this.y+90<=dt.y+90)
						{
							return true;
						}
						if(this.x+80>=dt.x&&this.x+80<=dt.x+80&&this.y+90>=dt.y&&this.y+90<=dt.y+90)
						{
							return true;
						}
					}
					if(dt.fangxiang==3||dt.fangxiang==1)
					{
						if(this.x>=dt.x&&this.x<=dt.x+90&&this.y+90>=dt.y&&this.y+90<=dt.y+80)
						{
							return true;
						}
						
						if(this.x+80>=dt.x&&this.x+80<=dt.x+90&&this.y+90>=dt.y&&this.y+90<=dt.y+80)
						{
							return true;
						}
					}
				}
			}
			break;
		case 3:
			for(int i=0;i<dtk.size();i++)
			{
				DiTank dt=dtk.get(i);
				if(dt!=this)
				{
					if(dt.fangxiang==0||dt.fangxiang==2)
					{
						if(this.x+90>=dt.x && this.x+90<=dt.x+80 && this.y>=dt.y && this.y<=dt.y+90)
						{
							return true;
						}
						if(this.x+90>=dt.x && this.x+90<=dt.x+80 && this.y+80>=dt.y && this.y+80<=dt.y+90)
						{
							return true;
						}
					}
					if(dt.fangxiang==3||dt.fangxiang==1)
					{
						if(this.x+90>=dt.x && this.x+90<=dt.x+90 && this.y>=dt.y && this.y<=dt.y+80)
						{
							return true;
						}
						if(this.x+90>=dt.x && this.x+90<=dt.x+90 && this.y+80>=dt.y && this.y+80<=dt.y+80)
						{
							return true;
						}
					}
				}
			}
			
			break;
		}		
		return b;
	}
	public void run()
	{
		while(true)
		{
			switch(this.fangxiang)
		{
		case 0:
			for(int i=0;i<30;i++)
			{
				if(y>0&&!huxiangpengzhuang())
				{y-=sudu;}
				try{
					Thread.sleep(50);
				}
				catch(Exception e){}
			}
			break;
		case 1:
			for(int i=0;i<30;i++)
			{
				if(x>0&&!huxiangpengzhuang())
				{x-=sudu;}
				try{
					Thread.sleep(50);
				}
				catch(Exception e){}
			}
			break;
		case 2:
			for(int i=0;i<30;i++)
			{
				if(y<1200&&!huxiangpengzhuang())
				{y+=sudu;}
				try{
					Thread.sleep(50);
				}
				catch(Exception e){}
			}
			break;
		case 3:
			for(int i=0;i<30;i++)
			{
				if(x<1500&&!huxiangpengzhuang())
				{x+=sudu;}
				try{
					Thread.sleep(50);
				   }
				catch(Exception e){}
			}
			break;
		}
			this.fangxiang=(int)(Math.random()*4);//随机函数，随机产生0到1之间的数字 取整 就是0到3
			if(this.shengming==false)
			{
				break;
			}
			time++;
			if(time%5==0)
			{
				if(shengming)
				{
					if(dzd.size()<5)
					{
						Zidan zidan=null;
						switch(fangxiang)
								{
								case 0:
									zidan=new Zidan(x+35,y-30,0);
									dzd.add(zidan);
									new Thread(zidan).start();
									break;
								case 1:
									zidan=new Zidan(x-30,y+35,1);
									dzd.add(zidan);
									new Thread(zidan).start();
								case 2:
									zidan=new Zidan(x+35,y+100,2);
									dzd.add(zidan);
									new Thread(zidan).start();
									break;
								case 3:
									zidan=new Zidan(x+105,y+35,3);
									dzd.add(zidan);
									new Thread(zidan).start();
							break;
								}

					}
				}
			}
//			if(dtk.size()==0){
//				life=false;
//				ss.setLife(false);
//				new Pause(ss).start();
//			}
		}
	}
}
class MyTank extends Tank 
{
	Vector<Zidan>aa=new Vector<Zidan>();//利用向量来装zidan 这样就可以防止只能发一发子弹
	Zidan zidan=null;
	public MyTank(int x,int y)
	{
		super(x,y);
	}
	public void xiangshang()
	{
		y-=sudu;
	}
	public void xiangxia()
	{
		y+=sudu;
	}
	public void xiangzuo()
	{
		x-=sudu;
	}
	public void xiangyou()
	{
		x+=sudu;
	}
	public void shot()
	{
		if(this.shengming){
		switch(this.fangxiang)
		{
		case 0:
			zidan=new Zidan(x+35,y-30,0);
			aa.add(zidan);
			break;
		case 1:
			zidan=new Zidan(x-30,y+35,1);
			aa.add(zidan);
			break;
		case 2:
			zidan=new Zidan(x+35,y+100,2);
			aa.add(zidan);
			break;
		case 3:
			zidan=new Zidan(x+105,y+35,3);
			aa.add(zidan);
			break;
		}
		Thread t=new Thread(zidan);
		t.start();
	}
	}
}
class Zidan implements Runnable
{
	int x;
	int y;
	int fangxiang;
	int sudu=8;
	boolean pp=true;
	public Zidan(int x,int y,int fangxiang)
	{
		this.x=x;
		this.y=y;
		this.fangxiang=fangxiang;
	}
	public void run() {
		while(true)//线程下面的死循环里都要有睡眠
		{
			try
			{
				Thread.sleep(50);
			}catch(Exception e){}
			switch(fangxiang)
			{
			case 0:
				y-=sudu;
				break;
			case 1:
				x-=sudu;
				break;
			case 2:
				y+=sudu;
				break;
			case 3:
				x+=sudu;
				break;
			}
			if(x<0||y<0||x>1500||y>1200)
			{
				pp=false;
				break;
			}
		}
	}
}
class Baozha
{
	int x,y;
	int shengcunqi=9;
	boolean shengming=true;
	public Baozha(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	public void suqsd()
	{
		if(shengcunqi>0)
		{
			shengcunqi--;
		}
		else
			this.shengming=false;
	}
}
class Jzjl{
	private static int dtsl=3;
	private static int mtsl=1;

	public static int getMtsl() {
		return mtsl;
	}

	public static void setMtsl(int mtsl) {
		Jzjl.mtsl = mtsl;
	}

	public static int getDtsl() {
		return dtsl;
	}

	public static void setDtsl(int dtsl) {
		Jzjl.dtsl = dtsl;
	}
	public static void jzdf(){
		dtsl--;
	}
	
}
class Start extends Thread{
    JLabel ms,s,m,h;
    boolean life=true;
    public boolean isLife() {
		return life;
	}
	public void setLife(boolean life) {
		this.life = life;
	}
	
    public Start(JLabel h,JLabel m,JLabel s,JLabel ms){
        this.h=h;
        this.m=m;
        this.s=s;
        this.ms=ms;
    }
    @Override
    public void run() {
    	
        int time_s,time_m,time_h,time_ms;
        time_ms=Integer.parseInt(ms.getText());
        time_s=Integer.parseInt(s.getText());
        time_m=Integer.parseInt(m.getText());
        time_h=Integer.parseInt(h.getText());

        NumberFormat nf = NumberFormat.getInstance();   //调整时间的输出格式
        NumberFormat nff = NumberFormat.getInstance();
        nf.setMinimumIntegerDigits(2) ;     //让时分秒都显示为两位数
        nff.setMinimumIntegerDigits(3) ;
        while (life) {
            try {
                Thread.sleep(1);     
            } catch (InterruptedException e) {
                break;  //如果中断此线程，跳出死循环从而结束线程
            }
            time_ms++;
            if(time_ms>=1000){
            	time_s++;
            	time_ms=0;
            	if (time_s>=60) {
                  time_m++;
                  time_s=0;   //秒钟等于60，分钟加1，秒钟置0
                  if(time_m>=60){
                      time_h++;
                      time_m=0;
                      if(time_m>=24){
                          time_h=0;
                      }
                  }
              }
            }
            ms.setText(nff.format(time_ms));
            s.setText(nf.format(time_s));   //  格式化输出 e.g. 0:0:1——>00:00:01
            m.setText(nf.format(time_m));
            h.setText(nf.format(time_h));

        }
    }

}
class Pause extends Thread{
    Start ss;
    public Pause(Start ss) {
        this.ss=ss;
    }
    @Override
    public void run() {
        ss.interrupt();
    }

}
class Shengyin extends Thread{
//	private String wjm;
	Player player;
	InputStream input;
//	public Shengyin(InputStream input){
////		wjm=ypwj;
//		this.input=input;
//	}
	public Shengyin(InputStream input) {
		// TODO Auto-generated constructor stub
		this.input=input;
	}
	public synchronized void run(){
//		super.start();
		try {
			play();
		} catch (FileNotFoundException | JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void play() throws FileNotFoundException,JavaLayerException{
		BufferedInputStream buffer=new BufferedInputStream(input);
		player=new Player(buffer);
		player.play();
	}
//	@Override
//	public void run() {
//		File wjl=new File(wjm);
//		//音频格式处理
//		AudioInputStream ypsrl=null;
//		try {
//			ypsrl=AudioSystem.getAudioInputStream(wjl);
//		} catch (Exception e) {}
//		AudioFormat format=ypsrl.getFormat();
//		SourceDataLine aqsj=null;
//		DataLine.Info info=new DataLine.Info(SourceDataLine.class, format);
//		//安全式包装
//		try {
//			aqsj=(SourceDataLine) AudioSystem.getLine(info);
//			aqsj.open(format);
//		} catch (Exception e) {}
//		aqsj.start();
//		
//		int zjtj=0;
//		//缓冲
//		byte[] hczj=new byte[1024];
//		try{
//			while(zjtj!=-1){
//				zjtj=ypsrl.read(hczj,0,hczj.length);
//				if(zjtj>=0){
//					aqsj.write(hczj, 0, zjtj);
//					}
//			}
//		}catch(Exception e){}
//		finally{
//			aqsj.drain();
//			aqsj.close();
//		}
//	}
}
class Xiaochu implements Runnable{
	boolean b;
	Vector<DiTank> dtkxl=new Vector<>();
//public boolean Xiaochu(){
//	return b;
//}
	public void dtkx(Vector<DiTank> dtk){
		this.dtkxl=dtk;
	}

public void run() {
	while(true){
//		try {
//			Thread.sleep(10);
//		} catch (Exception e) {}
	for(int i=0;i<dtkxl.size();i++){
		DiTank dt=dtkxl.get(i);
		for(int j=0;j<dt.dzd.size();j++){
			Zidan zidan=dt.dzd.get(j);
			try {
				Thread.sleep(80);
			} catch (InterruptedException e) {}
			if(zidan.x==MyPanel.a&&zidan.y==MyPanel.b){
//				b=true;
				zidan.pp=false;
				
			}
		}
	}
  }
}
}
