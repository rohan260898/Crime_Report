package com.sp.crime.Ctl;

public interface OCRView {

	public String APP_CONTEXT = "/OnlineCrimeReport";
	public String PAGE_FOLDER = "/jsp";
	
	
	//Controller------------------------------
	public String WELCOME_CTL = APP_CONTEXT + "/index";
	public String LOGIN_CTL = APP_CONTEXT + "/login";
	public String USER_REGISTRATION_CTL = APP_CONTEXT +  "/register";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/forgetpassword";
	public String USER_LIST_CTL = APP_CONTEXT +  "/userlist";
	public String FIR_CTL = APP_CONTEXT +  "/firctl";
	public String ABOUT_CTL = APP_CONTEXT +  "/about";
	public String CRIMINAL_CTL = APP_CONTEXT +  "/criminalctl";
	public String CONTACT_CTL = APP_CONTEXT +  "/contact";
	public String FIR_LIST_CTL = APP_CONTEXT +  "/firlistctl";
	public String IMAGE_CTL = APP_CONTEXT + "/image";
	public String CRIMINAL_LIST_CTL = APP_CONTEXT +  "/criminallistctl";
	public String MYPROFILE_CTL = APP_CONTEXT +  "/myprofile";
	public String CRIME_CTL = APP_CONTEXT +  "/crime";
	public String CRIME_LIST_CTL = APP_CONTEXT +  "/crimelist";
	public String USER_CTL = APP_CONTEXT + "/user";
	public String ROLE_CTL = APP_CONTEXT + "/role";


	//View-------------------------------------
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/RegistrationView.jsp";
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
	public String FIR_VIEW = PAGE_FOLDER + "/FirView.jsp";
	public String ABOUT_VIEW = PAGE_FOLDER + "/AboutView.jsp";
	public String CRIMINAL_VIEW = PAGE_FOLDER + "/CriminalView.jsp";
	public String CONTACT_VIEW = PAGE_FOLDER + "/ContactView.jsp";
	public String FIR_LIST_VIEW = PAGE_FOLDER + "/FirListView.jsp";
	public String CRIMINAL_LIST_VIEW = PAGE_FOLDER + "/CriminalListView.jsp";
	public String MYPROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";
	public String CRIME_VIEW = PAGE_FOLDER + "/CrimeView.jsp";
	public String CRIME_LIST_VIEW = PAGE_FOLDER + "/CrimeListView.jsp";
    public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";
    public String ROLE_VIEW = PAGE_FOLDER + "/Role.jsp";
	public String INDEX_VIEW = PAGE_FOLDER + "/index.jsp";
	
}
