package com.kingswood.passwordmanager.persistent;

import java.util.List;

import com.kingswood.passwordmanager.PasswordVO;

public interface IPasswordDAO {

	public void insertPassword(PasswordVO passwordVO);
	
	public List<PasswordVO> selectAllPasswords();
	
	public void updatePassword(PasswordVO passwordVO);
	
	public void deletePassword(String name);
	
}
