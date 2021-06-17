package com.demo.persitence;

import com.demo.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;

/**
 * @author 32050
 */
public interface AccountRepository extends JpaRepository<Account, String> {
    /**
     * 通过username与password查找账户信息
     * @param username 用户名
     * @param password 密码
     * @return 返回账户信息，如果没找到则返回null
     */
    Account findAccountByUsernameAndPassword(String username, String password);

    /**
     * 同各国username查找账户信息
     * @param username 用户名
     * @return 返回账户信息，如果没有找到则返回null
     */
    Account findAccountByUsername(String username);

    /**
     * 根据账号关联角色主键信息查找账户信息
     * @param primaryKey 关联角色主键信息
     * @return 返回账号信息
     */
    Account findByRolePrimaryKey(String primaryKey);

    /**
     * 根据用户名主键删除账号信息
     * @param username 用户名
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    void deleteByUsername(String username);

    /**
     * 根据账号关联主键删除账号信息
     * @param rolePrimaryKey 账号关联角色主键
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    void deleteAllByRolePrimaryKey(String rolePrimaryKey);
}
