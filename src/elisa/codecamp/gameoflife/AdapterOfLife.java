package elisa.codecamp.gameoflife;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class AdapterOfLife extends BaseAdapter {
	private boolean alive = true;
	private static int COUNT = 100;
	private boolean[] cellVitals = new boolean[COUNT];
	private ImageView[] images = new ImageView[COUNT];
	private Context context;
	
	public AdapterOfLife(Context context) {
		this.context = context;
		for(int i = 0; i < cellVitals.length; i++) {
			cellVitals[i] = false;
			images[i] = new ImageView(context);
			images[i].setLayoutParams(new GridView.LayoutParams(85, 85));
			images[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
			images[i].setPadding(1, 1, 1, 1);
			images[i].setBackgroundColor(Color.WHITE);
		}
	}

	public int getCount() {
		return COUNT;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}
	
	   public View getView(int position, View convertView, ViewGroup parent) {
	        ImageView imageView;
	        if (convertView == null) { 
	        	imageView = images[position];
	        } else {
	            imageView = (ImageView) convertView;
	        }

	        return imageView;
	    }
	   
	   public void markCellAlive(int position) {
		   cellVitals[position] = true;
		   images[position].setBackgroundColor(Color.BLUE);
		   cellVitals[++position] = true;
		   images[position].setBackgroundColor(Color.BLUE);
		   cellVitals[++position] = true;
		   images[position].setBackgroundColor(Color.BLUE);
		   vitalizeCells();
	   }
	   private void vitalizeCells() {
		   List<Integer> aliveCells = new ArrayList<Integer>();
		   for(int i = 0; i < cellVitals.length; i++) {
			   int aliveNeighbours = calculateAliveNeigbours(i);
			   if(cellVitals[i] && (aliveNeighbours == 2 || aliveNeighbours == 3)) {
				   aliveCells.add(i);
			   }
			   if(!cellVitals[i] && aliveNeighbours == 3) {
				   aliveCells.add(i);
			   }
		   }
		   resetAllCells();
		   for(Integer idx : aliveCells) {
			   cellVitals[idx] = true;
			   images[idx].setBackgroundColor(Color.BLUE);
		   }
	   }
	   
	   private void resetAllCells() {
		   for(int i = 0; i < cellVitals.length; ++i) {
			   cellVitals[i] = false;
			   images[i].setBackgroundColor(Color.WHITE);
		   }
	   }
	   private int y(int position) {
		   String positionString = position + "";
		   if (positionString.length() == 1) {
			   return 0;
		   }
		   return Integer.valueOf(positionString.substring(0, 1));
	   }
	   private int x(int position) {
		   String positionString = position + "";
		   if (positionString.length() > 1) {
			   return Integer.valueOf(positionString.substring(1, 2));
		   }
		   return Integer.valueOf(positionString.substring(0, 1));
	   }
	   
	   private int position(int x, int y) {
		   StringBuffer positionBuff = new StringBuffer();
		   if(y > 0) {
			   positionBuff.append(y);
		   }
		   positionBuff.append(x);
		   
		   return Integer.valueOf(positionBuff.toString());
		   
	   }
	   private int calculateAliveNeigbours(int position) {
		   int x = x(position);
		   int y = y(position);
		   int nbrOfAliveCells = 0;
		   if(y > 0) {
			   int northPos = position(x, y - 1);
			   if(cellVitals[northPos]) {
				   nbrOfAliveCells++;
			   }
			   if(x > 0) {
				   int northWestPos = position(x - 1, y - 1);
				   if(cellVitals[northWestPos]) {
					   nbrOfAliveCells++;
				   }
			   }
			   if(x < 9) {
				   int northEastPos = position(x + 1, y - 1);
				   if(cellVitals[northEastPos]) {
					   nbrOfAliveCells++;
				   }
			   }
		   }
		   if(y < 9) {
			   int southPos = position(x, y + 1);
			   System.err.println("!!!!!!!!!!!!!!!x: " + x + ", y: " + y + ", southPos: " + southPos + ", position: " + position);
			   if(cellVitals[southPos]) {
				   nbrOfAliveCells++;
			   }
			   if(x > 0) {
				   int southWestPos = position(x - 1, y + 1);
				   if(cellVitals[southWestPos]) {
					   nbrOfAliveCells++;
				   }
			   }
			   if(x < 9) {
				   int southEastPos = position(x + 1, y + 1);
				   if(cellVitals[southEastPos]) {
					   nbrOfAliveCells++;
				   }
			   }
		   }
		   if(x > 0) {
			   int westPos = position(x - 1, y);
			   if(cellVitals[westPos]) {
				   nbrOfAliveCells++;
			   }
		   }
		   if(x < 9) {
			   int eastPos = position(x + 1, y);
			   if(cellVitals[eastPos]) {
				   nbrOfAliveCells++;
			   }
		   }
		   
		   return nbrOfAliveCells;
	   }
}
