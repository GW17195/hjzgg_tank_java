package hjzgg.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import hjzgg.id.IDblock;
import hjzgg.layer.TheLayer;
import hjzgg.set.MySet;
import hjzgg.size.TheSize;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class TankFrame extends JFrame{
	public static int ID = 0;
	public  int[][] sta = null;//��ͼ״̬���
	
	public int[][] getSta() {
		return sta;
	}

	public void setSta(int[][] sta) {
		this.sta = sta;
	}

	private JLayeredPane jlp = null;
	private ShapePane pl = null;
	private JPanel pr = null;
	private JPanel pu = null;
	private JPanel pd = null;
	
	public JLayeredPane getJlp(){
		return jlp;
	}
	
	public JPanel getPr(){
		return pr;
	}
	
	public void initMap(JLayeredPane xjlp){
		//homeͼƬ
		ShapePane home = new ShapePane("��.jpg", TheSize.block_width*4, TheSize.block_height*3);
		home.setBounds(12*TheSize.block_width, 17*TheSize.block_height, TheSize.home_width, TheSize.home_height);
		home.setId(IDblock.home);
		xjlp.add(home, TheLayer.wall, -1);
		MySet.getInstance().getOtherSet().add(home);
		if(sta != null){
			for(int i=0; i<sta.length; ++i){
				//System.out.print("{");//��ӡ���ɵĵ�ͼ
				for(int j=0; j<sta[i].length; ++j){
					ShapePane sp = null;
					int layer = -1;
//					System.out.print(sta[i][j]);
//					if(j==sta[i].length-1) System.out.print("}");
//					System.out.print(",");
					switch(sta[i][j]){
						case IDblock.grass:
							sp = new ShapePane("�ݵ�.png", TheSize.block_width, TheSize.block_height);
							sp.setOpaque(false);
							sp.setId(IDblock.grass);
							layer = TheLayer.grass;
							break;
						case IDblock.steel_wall:
							sp = new ShapePane("��ǽ.jpg", TheSize.block_width, TheSize.block_height);
							sp.setId(IDblock.steel_wall);
							layer = TheLayer.wall;
							break;
						case IDblock.wall:
							sp = new ShapePane("שǽ.jpg", TheSize.block_width, TheSize.block_height);
							sp.setId(IDblock.wall);
							layer = TheLayer.wall;
							break;
						case IDblock.water:
							sp = new ShapePane("ˮ.jpg", TheSize.block_width, TheSize.block_height);
							sp.setId(IDblock.water);
							layer = TheLayer.water;
							break;
						default:
					}
					if(sp != null){
						MySet.getInstance().getOtherSet().add(sp);
						sp.setBounds(j*TheSize.block_width, i*TheSize.block_height, TheSize.block_width, TheSize.block_height);;
						xjlp.add(sp, layer, -1);
					}
				}
				//System.out.println();
			}
		}
	}
	
	public TankFrame(int[][] sta){
		super("HJZGG-TankBattle");
		this.sta = sta;
		jlp = new JLayeredPane();
		pl = new ShapePane("�����.png", 100, 700);
		pr = new JPanel();
		pu = new JPanel();
		pd = new JPanel();
		pu.add(new JLabel("̹�˴�ս-------hjzg"));
		JLabel tankCnt = new JLabel("��ɱ̹������:");
		pr.add(tankCnt);
		setSize(1217, 800);
		jlp.setOpaque(true);
		jlp.setBackground(Color.BLACK);
		initMap(jlp);
		pl.setPreferredSize(new Dimension(100, 0));
		pr.setPreferredSize(new Dimension(120, 0));
		pu.setPreferredSize(new Dimension(0, 20));
		pd.setPreferredSize(new Dimension(0, 20));
		jlp.setPreferredSize(new Dimension(980, 700));
		jlp.setLayout(null);
		
		setLayout(new BorderLayout());
		add(pl, BorderLayout.WEST);
		add(pr, BorderLayout.EAST);
		add(pd, BorderLayout.SOUTH);
		add(pu, BorderLayout.NORTH);
		add(jlp, BorderLayout.CENTER);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);// ��ֹ��ǰ�������е� Java �������
			}
		});
	}
	
}