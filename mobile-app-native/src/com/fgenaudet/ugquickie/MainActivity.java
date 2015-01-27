package com.fgenaudet.ugquickie;

import java.lang.reflect.Type;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fgenaudet.ugquickie.bean.Quicky;
import com.fgenaudet.ugquickie.service.QuickieService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends ActionBarActivity {
	private static final String QUICKY_KEY = "quicky_id";

	// LIST
	ListView listview;

	// DETAIL
	TextView title;
	TextView presenter;
	TextView location;
	TextView description;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
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
	public class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);

			listview = (ListView) rootView.findViewById(R.id.listView1);
			new LoadQuickiesTask().execute();

			return rootView;
		}
	}

	public class PlaceholderDetailFragment extends Fragment {

		public PlaceholderDetailFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.frament_detail, container, false);

			title = (TextView) rootView.findViewById(R.id.titleView);
			description = (TextView) rootView.findViewById(R.id.descriptionView);
			presenter = (TextView) rootView.findViewById(R.id.presenterView);
			location = (TextView) rootView.findViewById(R.id.locationView);

			new LoadQuickyTask().execute(getArguments().getString(QUICKY_KEY));

			return rootView;
		}
	}

	private class LoadQuickyTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... id) {
			return new QuickieService().loadOne(id[0]);
		}

		// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(String result) {
			Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();

			final Quicky value = new Gson().fromJson(result, Quicky.class);
			title.setText(value.getTitle());
			description.setText(value.getDescription());
			presenter.setText(value.getPresenterName());
			location.setText(value.getLocation());
		}
	}

	private class LoadQuickiesTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... id) {
			return new QuickieService().load();
		}

		// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(String result) {
			Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();

			Type collectionType = new TypeToken<List<Quicky>>() {
			}.getType();
			final List<Quicky> values = new Gson().fromJson(result, collectionType);

			ArrayAdapter<Quicky> adapter = new ArrayAdapter<Quicky>(getBaseContext(),
			            android.R.layout.simple_list_item_2, android.R.id.text1, values) {
				@Override
				public View getView(int position, View convertView, ViewGroup parent) {
					View view = super.getView(position, convertView, parent);
					TextView text1 = (TextView) view.findViewById(android.R.id.text1);
					TextView text2 = (TextView) view.findViewById(android.R.id.text2);

					text1.setText(values.get(position).getTitle());
					text2.setText(values.get(position).getDescription());
					return view;
				}
			};

			listview.setAdapter(adapter);
			listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				public void onItemClick(android.widget.AdapterView<?> parent, View view, int position, long id) {
					// Create a new fragment and specify the planet to show
					// based on position
					Fragment fragment = new PlaceholderDetailFragment();
					Bundle args = new Bundle();
					args.putString(QUICKY_KEY, values.get(position).getId());
					fragment.setArguments(args);

					// Insert the fragment by replacing any existing fragment
					FragmentManager fragmentManager = getSupportFragmentManager();
					FragmentTransaction ft = fragmentManager.beginTransaction();
					ft.replace(R.id.container, fragment);
					ft.addToBackStack("tag");
					ft.commit();
				};
			});
		}
	}
}
