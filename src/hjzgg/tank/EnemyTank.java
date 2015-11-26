package hjzgg.tank;

import hjzgg.main.TankFrame;

import java.util.Random;

import javax.swing.JLayeredPane;

public class EnemyTank extends Tank implements Runnable{
	//̹�˵��ٶ�e
	private int speed = 0;
	private int f_sleep = 0;//̹���ƶ���Ƶ��
	public EnemyTank(String type, String path, int id, TankFrame tf){
		super(type, path, id, tf);
		flag = true;
		if(type.equals("enemy3")) {
			speed = 15;
			f_sleep = 100;
		}
		else {
			speed = 10;
			f_sleep = 500;
		}
	}
	public void run(){
		Random rd = new Random();
		while(flag){
			int key = Math.abs(rd.nextInt())%6;
				switch(key){
					case 0:
					case 1:
					case 2:
						if(getCurDir()==Tank.down) move(speed);
						break;
					case 3://��ǰ�����ƶ�
						move(speed);
						break;
					case 4://ת��
						int dir = Math.abs(rd.nextInt())%4 + 1;
						if(dir == getCurDir()){
							move(speed);
							break;
						}
						String dirStr = null;
						switch(dir){
							case 1:
								dirStr = "left";
								break;
							case 2:
								dirStr = "up";
								break;
							case 3:
								dirStr = "down";
								break;
							case 4:
								dirStr = "right";
								break;
						}
						if(dirStr != null && flag){
							setCurDir(dir);
							turn("tank/" + getTankType() + "_" + dirStr + ".png");
						}
						break;
					default://���ֲ���
						key = Math.abs(rd.nextInt())%100;
						if(key % 5 ==0)
							shell();//�����ڵ�
						break;
				}
				try {
					Thread.sleep(f_sleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
}
