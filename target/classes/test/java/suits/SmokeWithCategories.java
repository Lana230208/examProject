package suits;

//import categories.SmokeTestFilter;
import loginTest.LoginTestWithPageObject;
//import loginTest.RegistrationTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
//@Categories.IncludeCategory(SmokeTestFilter.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
//        RegistrationTest.class
})
public class SmokeWithCategories {
}
