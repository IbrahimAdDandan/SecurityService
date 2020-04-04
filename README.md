# Java Spring Startup project

## Dynamic Role and privilege layer

### Database setting
we use mysqlDB
* you can set DB connection variables in application.properties file

### Initial Data

By default an admin user will be created, with credentials:
* username: admin
* password: admin  

### Define Entity

```java
@Entity
public class MyEntity extends BaseEntity {
// your code
}
```

BaseEntity already contain: 
1. auto-generated Id
2. createdDate
3. isDeleted

### Service
You can extends from the GenericService class
```java
@Service
public class MyService extends GenericService<MyEntity extends BaseModel> {
}
```
GenericService contains the methods:
1. getAll
2. getOne
3. delete (update record: set isDeleted to true)
4. save
5. saveAll

### Define Controller

```java
@RestController
@RequestMapping("path")
public class MyController extends BaseController {

    @Autowired
    public Test(PrivilegeService privilegeService) {
        super(privilegeService);
    }
}
```
Auto-wiring PrivilegeService will create default Privileges 
for this controller based on controller name as:
1. GET_MyController
2. ADD_MyController
3. UPDATE_MyController
4. DELETE_MyController
then you can use AuthorityHelper class for authorization.

### Authorization
in any method in controller you can use the three method:
1. `AuthorityHelper.hasAuthority(UserDetails user, String className, OperationType operationType)`
2. `AuthorityHelper.hasAuthority(UserDetails user, String privilege)`
3. `AuthorityHelper.hasAuthority(List<GrantedAuthority> authorities, String privilege)`

### For further reference
please consider the following sections:
    
* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/maven-plugin/)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/htmlsingle/#boot-features-security)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/htmlsingle/#using-boot-devtools)


> Ibrahim Ad_Dandan