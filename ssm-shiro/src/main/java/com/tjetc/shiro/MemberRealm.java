package com.tjetc.shiro;

import com.tjetc.entity.Member;
import com.tjetc.service.MemberService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 查询数据库，获取登录的用户和权限信息，给shiro使用
 */
public class MemberRealm extends AuthorizingRealm {
    @Autowired
    private MemberService memberService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return super.supports(token);
    }

    /**
     * 获取用户权限信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取用户名
        String username = (String) principalCollection.getPrimaryPrincipal();
        List<String> permissionNames = memberService.findPermissionNamesByUsername(username);
        //生成权限信息对象 SimpleAuthorizationInfo
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //角色暂时不用
        //simpleAuthorizationInfo.addRoles();
        //添加权限
        simpleAuthorizationInfo.addStringPermissions(permissionNames);
        //返回权限信息对象
        return simpleAuthorizationInfo;
    }

    /**
     * 获取用户认证信息
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户名
        String username = (String) authenticationToken.getPrincipal();
        //根据用户名查询用户信息
        Member member = memberService.findByUsername(username);
        if (member == null) {
            //直接抛出异常，表示用户名称不存在
            throw new UnknownAccountException("用户名不存在");
        }
        //实例化SimpleAuthenticationInfo对象，需要三个参数：正确：用户名、密码、realname
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(member.getUsername(), member.getPassword(), getName());
        //返回对象
        return simpleAuthenticationInfo;
    }
}
