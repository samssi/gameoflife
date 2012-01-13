package elisa.codecamp.gameoflife;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class GameoflifeActivity extends Activity {
	private AdapterOfLife adapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupGrid();

	}

	private void setupGrid() {
		GridView gridView = (GridView) findViewById(R.id.gridView1);
		adapter = new AdapterOfLife(this);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {	
				adapter.markCellAlive(position);
			}
		});
	}

}