package com.kingswood.passwordmanager.persistent;

import java.util.List;

import com.kingswood.passwordmanager.activity.PasswordVO;

public interface IPasswordDAO {

	public void insertPassword(PasswordVO passwordVO);
	
	public List<PasswordVO> selectAllPasswords();
	
	public void updatePassword(String originalTitle, PasswordVO passwordVO);
	
	public void deletePassword(String name);
	
	public PasswordVO selectPasswordByTitle(String title);
	
	public List<PasswordVO> selectPasswordsByNameOrDescription(String nameOrDescription);
	
}
