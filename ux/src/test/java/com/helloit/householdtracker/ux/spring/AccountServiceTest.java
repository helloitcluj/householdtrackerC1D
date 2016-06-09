package com.helloit.householdtracker.ux.spring;

import com.helloit.householdtracker.ux.common.IAccountService;
import com.helloit.householdtracker.ux.common.entities.User;
import com.helloit.householdtracker.ux.common.repository.IUserRepository;
import com.helloit.householdtracker.ux.spring.account.AccountService;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

public class AccountServiceTest {

    @Test
    public void missingUsername(){
        IAccountService.CreationOutcomes result;
        IUserRepository userRepository = getMockedUserRepository();
        IAccountService accountService = new AccountService(userRepository);

        result = accountService.registerAccount("", "welcome", "welcome");
        Assert.assertEquals("missing username", IAccountService.CreationOutcomes.MISSING_USERNAME, result);
    }

    @Test
    public void missingPassword(){
        IAccountService.CreationOutcomes result;
        IUserRepository userRepository = getMockedUserRepository();
        IAccountService accountService = new AccountService(userRepository);

        result = accountService.registerAccount("testUser", "", "");
        Assert.assertEquals("missing password", IAccountService.CreationOutcomes.MISSING_PASSWORD, result);
    }

    @Test
    public void passwordMismatch(){
        IAccountService.CreationOutcomes result;
        IUserRepository userRepository = getMockedUserRepository();
        IAccountService accountService = new AccountService(userRepository);

        result = accountService.registerAccount("testUser", "welcome", "welcome1");
        Assert.assertEquals("retype password", IAccountService.CreationOutcomes.RETYPED_PASSWORD_DO_NOT_MATCH, result);
    }

    @Test
    public void existingAccount(){
        IAccountService.CreationOutcomes result;
        IUserRepository userRepository = getMockedUserRepository();
        IAccountService accountService = new AccountService(userRepository);

        result = accountService.registerAccount("testUser", "welcome", "welcome");
        Assert.assertEquals("missing username", IAccountService.CreationOutcomes.EXISTING_ACCOUNT, result);
    }

    @Test
    public void successfulRegistration(){
        IAccountService.CreationOutcomes result;
        IUserRepository userRepository = getMockedUserRepository2();
        IAccountService accountService = new AccountService(userRepository);

        result = accountService.registerAccount("testUserNew", "welcome", "welcome");
        Assert.assertEquals("missing username", IAccountService.CreationOutcomes.SUCCESS, result);
    }


    @Test
    public void missingLoginUsername(){
        IAccountService.CreationOutcomes result;
        IUserRepository userRepository = getMockedUserRepository();
        IAccountService accountService = new AccountService(userRepository);

        result = accountService.loginAccount("", "test");

        Assert.assertEquals("missing username", IAccountService.CreationOutcomes.MISSING_USERNAME, result);
    }

    @Test
    public void missingLoginPassword(){
        IAccountService.CreationOutcomes result;
        IUserRepository userRepository = getMockedUserRepository();
        IAccountService accountService = new AccountService(userRepository);

        result = accountService.loginAccount("testUser", "");

        Assert.assertEquals("missing username", IAccountService.CreationOutcomes.MISSING_PASSWORD, result);
    }

    @Test
    public void invalidCredentials(){
        IAccountService.CreationOutcomes result;
        IUserRepository userRepository = getMockedUserRepository();
        IAccountService accountService = new AccountService(userRepository);

        result = accountService.loginAccount("testUser", "welcomeWrong");

        Assert.assertEquals("missing username", IAccountService.CreationOutcomes.INVALID_CREDENTIAL, result);
    }

    @Test
    public void successfulLogin(){
        IAccountService.CreationOutcomes result;
        IUserRepository userRepository = getMockedUserRepository2();
        IAccountService accountService = new AccountService(userRepository);

        result = accountService.loginAccount("testUser", "welcomeWrong");

        Assert.assertEquals("missing username", IAccountService.CreationOutcomes.SUCCESS, result);
    }

    @NotNull
    private IUserRepository getMockedUserRepository() {
        return new EmptyMockUserRepository() {

            @Override
            public List<User> findByUsername(String username){
                List<User> users = new ArrayList();
                User user = new User();
                user.setUserName("testUser");
                user.setPassword("welcome");

                users.add(user);
                return users;
                //return null;
            }

            @Override
            public List<User> findByUsernameAndPassword(String username, String password){
                List<User> users = new ArrayList();
                return users;
            }
        };
    }


    @NotNull
    private IUserRepository getMockedUserRepository2() {
        return new EmptyMockUserRepository() {

            @Override
            public List<User> findByUsername(String username){
                List<User> users = new ArrayList();
                return users;
                //return null;
            }

            @Override
            public List<User> findByUsernameAndPassword(String username, String password){
                List<User> users = new ArrayList();
                User user = new User();
                user.setUserName("testUser1");
                user.setPassword("welcome");

                users.add(user);
                return users;
            }


        };
    }

    private static class EmptyMockUserRepository implements IUserRepository {

        @Override
        public List<User> findByUsername(String username) {
            return null;
        }

        @Override
        public List<User> findByUsernameAndPassword(String username, String password) {
            return null;
        }

        @Override
        public <S extends User> S save(S entity) {
            return null;
        }

        @Override
        public User findOne(Integer integer) {
            return null;
        }

        @Override
        public boolean exists(Integer integer) {
            return false;
        }

        @Override
        public List<User> findAll() {
            return null;
        }

        @Override
        public List<User> findAll(Sort sort) {
            return null;
        }

        @Override
        public Page<User> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public List<User> findAll(Iterable<Integer> integers) {
            return null;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void delete(Integer integer) {

        }

        @Override
        public void delete(User entity) {

        }

        @Override
        public void delete(Iterable<? extends User> entities) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public void flush() {

        }

        @Override
        public void deleteInBatch(Iterable<User> entities) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public User getOne(Integer integer) {
            return null;
        }

        @Override
        public <S extends User> S saveAndFlush(S entity) {
            return null;
        }

        @Override
        public <S extends User> List<S> save(Iterable<S> entities) {
            return null;
        }
    }
}
