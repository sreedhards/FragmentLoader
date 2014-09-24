package com.example.fragmentloader;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment implements LoaderCallbacks<Cursor> {

		CursorAdapter adapter ;
		Cursor cursor1 = null;
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			ListView  list = (ListView) rootView.findViewById(R.id.listView1);
			
			 adapter = new CursorAdapter(rootView.getContext(), cursor1) {
				
				@Override
				public View newView(Context context, Cursor cursor, ViewGroup parent) {
					// TODO Auto-generated method stub
					LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					View rowView = inflater.inflate(R.layout.row, parent,
							false);
					
					return rowView;
				}
				
				@Override
				public void bindView(View view, Context context, Cursor cursor) {
					
					
					TextView number = (TextView) view.findViewById(R.id.textnumber);
					TextView name = (TextView) view.findViewById(R.id.textName);
					
					name.setText(cursor.getString(0));
					number.setText(cursor.getString(1));
					
				}
			};
			list.setAdapter(adapter);
			
	
			getLoaderManager().initLoader(1, null,(LoaderCallbacks<Cursor>) this);
			return rootView;
		}

		@Override
		public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
			
			Uri  mContactUri = Contacts.CONTENT_URI; 
					
			 String[] projection = {					 
					 
					 Contacts.DISPLAY_NAME,
					 Contacts.DISPLAY_NAME
					 					 
			 };
			 
			
			return new CursorLoader(getActivity(), mContactUri, projection, null, null, null);
		}


		@Override
		public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
			
			adapter.swapCursor(arg1);
		}

		@Override
		public void onLoaderReset(Loader<Cursor> arg0) {
			adapter.swapCursor(null);
			
		}

		
		
		
		
	}

}
