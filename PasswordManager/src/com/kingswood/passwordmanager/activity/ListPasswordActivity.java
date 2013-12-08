package com.kingswood.passwordmanager.activity;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SearchView;

import com.kingswood.passwordmanager.R;
import com.kingswood.passwordmanager.persistent.IPasswordDAO;
import com.kingswood.passwordmanager.persistent.PasswordDAO;
import com.kingswood.passwordmanager.util.DataUtil;
import com.kingswood.passwordmanager.util.PMLog;

@SuppressLint("NewApi")
public class ListPasswordActivity extends ListActivity implements
		SearchView.OnQueryTextListener {

	private SearchView mSearchView;

	private String searchFilter;
	
	private ListPasswordAdapter adapter;
	
	List<PasswordVO> passwordList = null;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		

		DataUtil.initializeData(getApplicationContext());

		IPasswordDAO dao = new PasswordDAO(getApplicationContext());

		if (null == searchFilter) {
			passwordList = dao.selectAllPasswords();
		} else {
			passwordList = dao.selectPasswordsByNameOrDescription(searchFilter);
		}

		adapter = new ListPasswordAdapter(passwordList,
				this.getBaseContext());

		setListAdapter(adapter);
		
		//ListView listView = (ListView)findViewById(android.R.id.list);
		
		//listView.setOnItemClickListener(new listItemOnClickListener());

	}
	
	class listItemOnClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			System.out.println("position : " + position);
			PasswordVO password = passwordList.get(position);
			
			System.out.println("id : " + id);
			
			System.out.println(password.getName());
		}
		
	}

	/*@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.menu_create_new:
			PMLog.log("click menu option of 'new password'");
			Intent intent = new Intent();
			intent.setClass(this, AddPasswordActivity.class);
			startActivity(intent);
			return true;
		case R.id.menu_edit:
			PMLog.log("click menu option of 'edit passwords'");
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	*/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.list_password_menu, menu);

		MenuItem searchItem = menu.findItem(R.id.action_search);
		mSearchView = (SearchView) searchItem.getActionView();
		setupSearchView(searchItem);

		return true;
	}

	private void setupSearchView(MenuItem searchItem) {
		if (isAlwaysExpanded()) {
			mSearchView.setIconifiedByDefault(false);
		} else {
			searchItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM
					| MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
		}

		mSearchView.setOnQueryTextListener(this);
	}

	protected boolean isAlwaysExpanded() {
		return true;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		return null;

	}

	@Override
	public boolean onQueryTextChange(String arg0) {
		PMLog.log("onQueryTextChange----------");
		searchFilter = arg0;
		PMLog.log("searchFilter is ---------- " + searchFilter);
		
		IPasswordDAO dao = new PasswordDAO(getApplicationContext());

		
		if (null == searchFilter) {
			passwordList = dao.selectAllPasswords();
		} else {
			passwordList = dao.selectPasswordsByNameOrDescription(searchFilter);
		}
		
		PMLog.log("password list size is : " + passwordList.size() + " after filtered by '" + searchFilter + "'");
		
		adapter.setPasswordList(passwordList);
		
		adapter.notifyDataSetChanged();

		return true;
	}

	@Override
	public boolean onQueryTextSubmit(String arg0) {
		PMLog.log("onQueryTextSubmit----------");
		return true;
	}
	
	public void processDelete(View view){
		PMLog.log("processing delete event----------");
	}
	

}
