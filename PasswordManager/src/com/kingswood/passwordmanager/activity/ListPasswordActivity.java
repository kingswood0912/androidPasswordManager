package com.kingswood.passwordmanager.activity;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Context;
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
	
	public static final String ACTION = "action";
	
	public static final String ACTION_ADD = "add";
	
	public static final String ACTION_UPDATE = "update";
	
	public static final String TITLE = "title";

	private SearchView mSearchView;

	private String searchFilter;
	
	private ListPasswordAdapter adapter;
	
	List<PasswordVO> passwordList = null;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.layout_list_password);

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
		
		ListView listView = (ListView)findViewById(android.R.id.list);
		
		listView.setOnItemClickListener(new ListItemOnClickListener(this.getApplicationContext()));

	}
	
	class ListItemOnClickListener implements OnItemClickListener {

		private Context context = null;

		public ListItemOnClickListener(Context context) {
			this.context = context;
		}

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {

			PasswordVO password = passwordList.get(position);
			
			PasswordDetailDialogFragment detailDialogFragment = new PasswordDetailDialogFragment();
			Bundle bundle = new Bundle();
			bundle.putString(ACTION, ACTION_UPDATE);
			bundle.putString(TITLE, password.getTitle());
			detailDialogFragment.setArguments(bundle);
			detailDialogFragment.show(ListPasswordActivity.this.getFragmentManager(), null);

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
		
		MenuItem addItem = menu.findItem(R.id.action_add);
		
		MenuItemClickListener menuItemClickListener = new MenuItemClickListener(this.getApplicationContext());
		
		addItem.setOnMenuItemClickListener(menuItemClickListener);
		

		MenuItem searchItem = menu.findItem(R.id.action_search);
		mSearchView = (SearchView) searchItem.getActionView();
		setupSearchView(searchItem);

		return true;
	}
	
	class MenuItemClickListener implements MenuItem.OnMenuItemClickListener{
		
		private Context context;
		
		public MenuItemClickListener(Context context){
			this.context = context;
		}
		
		public boolean onMenuItemClick(MenuItem item) {
			
			if(item.getItemId() == R.id.action_add){
				PasswordDetailDialogFragment detailDialogFragment = new PasswordDetailDialogFragment();
				Bundle bundle = new Bundle();
				bundle.putString(ACTION, ACTION_ADD);
				detailDialogFragment.setArguments(bundle);
				detailDialogFragment.show(ListPasswordActivity.this.getFragmentManager(), null);
			}else{
				PMLog.log("item id is : " + item.getItemId());
			}
			
			return true;
		}
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
		return false;
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
	
	// create new password, called by dialog window
	public void onCreateNewPassword(PasswordVO vo){
		
		// insert into database
		IPasswordDAO dao = new PasswordDAO(getApplicationContext());
		dao.insertPassword(vo);
		
		// refresh/reload current activity
		this.finish();
		startActivity(getIntent());
		
	}
	
	// update existing password, called by open dialog
	public void onUpdateExistingPassword(String originalTitle, PasswordVO vo) {
		IPasswordDAO dao = new PasswordDAO(getApplicationContext());
		dao.updatePassword(originalTitle, vo);

		// refresh/reload current activity
		this.finish();
		startActivity(getIntent());
	}
	

}
