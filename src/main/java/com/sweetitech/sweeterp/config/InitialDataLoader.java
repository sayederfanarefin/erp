package com.sweetitech.sweeterp.config;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.sweetitech.sweeterp.model.customer.Customer;
import com.sweetitech.sweeterp.model.customer.CustomerDetails;
import com.sweetitech.sweeterp.model.product.Pricing;
import com.sweetitech.sweeterp.model.product.PricingType;
import com.sweetitech.sweeterp.model.product.Product;
import com.sweetitech.sweeterp.model.product.ProductCategory;
import com.sweetitech.sweeterp.model.product.ProductDetails;
import com.sweetitech.sweeterp.model.user.Area;
import com.sweetitech.sweeterp.model.user.Division;
import com.sweetitech.sweeterp.model.user.EmployeeDetails;
import com.sweetitech.sweeterp.model.user.Privilege;
import com.sweetitech.sweeterp.model.user.Role;
import com.sweetitech.sweeterp.model.user.Salary;
import com.sweetitech.sweeterp.model.user.Territory;
import com.sweetitech.sweeterp.model.user.User;
import com.sweetitech.sweeterp.repository.AreaRepository;
import com.sweetitech.sweeterp.repository.CustomerRepository;
import com.sweetitech.sweeterp.repository.DivisionRepository;
import com.sweetitech.sweeterp.repository.PricingRepository;
import com.sweetitech.sweeterp.repository.PricingTypeRepository;
import com.sweetitech.sweeterp.repository.PrivilegeRepository;
import com.sweetitech.sweeterp.repository.ProductCategoryRepository;
import com.sweetitech.sweeterp.repository.ProductRepository;
import com.sweetitech.sweeterp.repository.RoleRepository;
import com.sweetitech.sweeterp.repository.SalaryRepository;
import com.sweetitech.sweeterp.repository.TerritoryRepository;
import com.sweetitech.sweeterp.repository.UsersRepository;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private SalaryRepository salaryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private PricingRepository pricingRepository;
	
	@Autowired
	private PricingTypeRepository pricingTypeRepository;
	
	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	@Autowired
	private PrivilegeRepository privilegeRepository;
	
	@Autowired
	private DivisionRepository divisionRepository;
	@Autowired
	private AreaRepository areaRepository;
	@Autowired
	private TerritoryRepository territoryRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	

	// API

	@Override
	@Transactional
	public void onApplicationEvent(final ContextRefreshedEvent event) {

		List<Privilege> allPrivileges = new ArrayList<Privilege>();

		Class cls;
		try {
			cls = Class.forName("com.sweetitech.sweeterp.config.PrivilegeConstants");

			// PrivilegeConstants uuiDd=new PrivilegeConstants();
			Field[] fields = cls.getDeclaredFields(); // get all declared fields
			for (Field field : fields) {
				if (field.getType().equals(String.class)) { // if it is a String field
					// System.out.println("Variable name: "+field.getName());
					field.setAccessible(true);

					// System.out.println("Variable value: "+field.get(cls));

					String prev = field.get(cls).toString();
					allPrivileges.add(createPrivilegeIfNotFound(prev));

				}
			}

		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// == create initial privileges
		// final Privilege superAdminPrivilege =
		// createPrivilegeIfNotFound(PrivilegeConstants.SUPER_ADMIN);

		// == create initial roles
		// final List<Privilege> adminPrivileges = new
		// ArrayList<Privilege>(Arrays.asList(privilegeRepository.findByName(Priv)));

		final Role adminRole = createRoleIfNotFound(Constants.SUPER_ADMIN, allPrivileges);

		
		EmployeeDetails employeeDetails = new EmployeeDetails();
		employeeDetails.setAddress("Mohammadpur, Dhaka - 1207");
		employeeDetails.setPhone("01521107480");
		employeeDetails.setDateOfBirth(new Date("02/08/1994"));
		employeeDetails.setDesignation("System");
		employeeDetails.setFirstName("Bla bla bla");
		employeeDetails.setLastName("sdfsdfjsfl ksdfk");
		employeeDetails.setJoiningDate(new Date("2/4/2016"));
		
		Salary salary = new Salary();
		salary.setAllowance(900000);
		salary.setGross(459409);
		salary.setHouseRent(540000);
		salary.setInsurance(90004);
		salary.setMedical(345345);
		salary = salaryRepository.save(salary);
		employeeDetails.setSlaray(salary);
		
		
		//createUserIfNotFound("admin@sweetiTech.com", "Sweet", "Sweet", "Sweet",
			//	new ArrayList<Role>(Arrays.asList(adminRole)), employeeDetails);
		
		Division division = createDivisionIfNotFound("Dhaka");
		Area area = createAreaIfNotFound("Mohammadpur", division);
		createTerritoryIfNotFound("Tazmahal road", area);
		Territory t = createTerritoryIfNotFound("Nurjahan road", area);
		
		
		// == create initial user
			User user =	createUserIfNotFound("test@test.com", "Test", "Test", "test", new ArrayList<Role>(Arrays.asList(adminRole)), employeeDetails, t);

				
		ProductCategory	pc = 	createProductCategoryIfNotFound("Test Product Category");
		ProductDetails pd = new ProductDetails();
		pd.setDetails("bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla ");
		pd.setProductCategory(pc);
		
		Product product = createProductIfNotFound("product name", pd, 459687, "Bla Unit");
		
		
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setAddress("customer address");
		customerDetails.setComment("comment");
		customerDetails.setContactPerson("contact person dfkngkjdfngdg");

		customerDetails.setEmail("customer@sweetitech.com");
		customerDetails.setDateOfBirth(new Date("2/4/2016"));
		
		
		Customer customer = createCustomerIfNotFound("customer name", user, customerDetails, t);
		
		createPricingTypeIfNotFound("cash");
		PricingType pt = createPricingTypeIfNotFound("credit");
		createPricingIfNotFound(customer.getName()+" : " + product.getParticulars()+" : " + pt.getName() ,customer, product, pt );
	}

	
	
	@Transactional
	private final Customer createCustomerIfNotFound(final String name, User customerCreatedBy, CustomerDetails customerDetails , Territory customerTeritory) {
		Customer customer = new Customer();
		customer.setName(name);
		customer.setCustomerCreatedBy(customerCreatedBy);
		
		
		customerDetails.setCustomer(customer);
		customer.setCustomerDetails(customerDetails);
		//customerTeritory.addCustomer(customer);
		customer.setCustomerTeritory(customerTeritory);
		
		
		return customerRepository.save(customer);
	}
	
	
	@Transactional
	private final Privilege createPrivilegeIfNotFound(final String name) {
		Privilege privilege = privilegeRepository.findByName(name);
		if (privilege == null) {
			privilege = new Privilege(name);
			String[] parts = name.split("_");
			privilege.setTag(parts[0]);
			privilege = privilegeRepository.save(privilege);
		}
		return privilege;
	}

	@Transactional
	private final Pricing createPricingIfNotFound(final String name, final Customer customer, final Product product, final PricingType pricingType) {
		
		Pricing pricing = new Pricing();
		pricing.setUnite_name(name);
		pricing.setCustomer(customer);
		pricing.setPricingType(pricingType);
		pricing.setProduct(product);
		
		return pricingRepository.save(pricing);
	}
	
	@Transactional
	private final PricingType createPricingTypeIfNotFound(final String name) {
		
		PricingType pricingType = new PricingType();
		pricingType.setName(name);
		
		return pricingTypeRepository.save(pricingType);
	}
	
	
	@Transactional
	private final Role createRoleIfNotFound(final String name, final List<Privilege> privileges) {
		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role(name);
		}
		role.setPrivileges(privileges);
		role = roleRepository.save(role);
		return role;
	}
	
	
	
	@Transactional
	private final Division createDivisionIfNotFound(final String name) {
		Division division = new Division();
		division.setName(name);
		divisionRepository.save(division);
		return division;
	}
	
	@Transactional
	private final Area createAreaIfNotFound(final String name, Division division) {
		
		Area area = new Area();
		area.setName(name);
		area.setDivision(division);
		
		area = areaRepository.save(area);
		
		division.addArea(area);
		divisionRepository.save(division);
		
		
		return area;
	}
	
	
	@Transactional
	private final Territory createTerritoryIfNotFound(final String name, Area area) {
		
		Territory territory = new Territory();
		territory.setName(name);
		territory.setArea(area);
		
		territory = territoryRepository.save(territory);
		
		area.addTerritory(territory);
		
		areaRepository.save(area);
		
		
		return territory;
	}

	@Transactional
	private final User createUserIfNotFound(final String email, final String firstName, final String lastName,
			final String password, final Collection<Role> roles, final EmployeeDetails employeeDetails, Territory territory) {

			User user = userRepository.findByEmail(email);
			if (user == null) {
				user = new User();

				user.setPassword(passwordEncoder.encode(password));
				user.setEmail(email);
				user.setEnabled(true);
				user.setRoles(roles);
				user.setEmployeeDetails(employeeDetails);
				user.getTerritories().add(territory);
				user = userRepository.save(user);
				
			}
			
		return user;
	}
	
	
	@Transactional
	private final ProductCategory createProductCategoryIfNotFound(final String name) {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setName(name);
		 productCategoryRepository.save(productCategory);
		 return productCategory;
	}
	@Transactional
	private final Product createProductIfNotFound(final String particulars, ProductDetails productDetails, long quantityPerPackage, String unit) {
		Product product = new Product();
		product.setParticulars(particulars);
		
		
		product.setUnit(unit);
		
		productDetails.setProduct(product);
		product.setProductDetails(productDetails);
		productRepository.save(product);
		return product;
	}

}