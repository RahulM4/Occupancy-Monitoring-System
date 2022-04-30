# OMSApp
Welcome to the IIITA !!

User Manual of Occupancy Monitoring System

Pre-requisites for project :-

1.) IDE - Android Studio
2.) UI/UX - XML
3.) Frontend - Java
4.) Backend - Firebase

-- For executing the project, execute it via Android Studio.

-- Brief of each and every interface's functionality are enumerated as under:

1.) Main Page :

-- The main components on the main page interface are : - Occupancy Status ( Available/Filled )
                                              - Maximum Capacity ( Numerical Value )
                                              - Available Seats  ( Numerical Value )
                                              - Sign In Button
                                              - Admin Sign In Button   

    -- Sign In Button : On clicking this button, individual moves to the user_login page.

    -- Admin Sign In Button : On clicking this button , admin moves to the admin_login page.This is only for administration use.

------------------------------

2.) Admin Login page :

-- The main components on the admin_login interface are : - Edittext of emailid and password
                                                          - Login Button

    -- Login button : If the email id and password are correct, then admin_dashboard interface is reflected on the screen.
                      Else, Error will be displayed.

    -- Correct Credentials : Emailid : admin@iiita.ac.in
                             Password : 123456

------------------------------

3.) Admin Dashboard page :

-- The main components on the admin_dashboard interface are : - Set Occupancy Limit Button
                                                              - Show Unique Users Database Button
                                                              - Show Timestamp Database Button

    -- Set Occupancy Limit button : This function is to set the max capacity limit of students in the building which is reflected on the main page.

    -- Show Unique Users Database Button : By Clicking on this button, admin can see the list of users.
  
------------------------------

4.) User Login page :

-- The main components on the user_login interface are :  - Edittext of emailid and password
                                                          - Login Button
                                                          - Forgot Password Button
                                                          - Create New Account Button

    -- Login button : If the email id and password are correct and activation link is already clicked sent on email id, then user_dashboard interface is reflected on the screen.
                      Else, Error will be displayed.
 
    -- Forgot Password Button : The function of this button is to send the reset password link on the given mail id.

    -- Create New Account Button : This button is for registration purpose for new users.

-------------------------------

5.) User Register page :

-- The main components on the user_register interface are : - Edittext of emailid,name,mobile number,enrollment number,password
                                                            - Register Button

    -- Register Button : If all the details are correctly matching with the constraints, then by clicking on the register button, an activation link is sent to the given mail id.
                         Else, error will be displayed.

-------------------------------

6.) User Dashboard

-- The main components on the user_dashboard interface are : - Profile details
                                                             - Enter Button
                                                             - Timestamp of entry and exit

    -- Enter Button : Clicking on the enter button infers that the person has entered to the building and along with this, the time and date is recorded.

    -- Exit Button : Clicking on the exit button infers that the person has exit from the building and time is also recorded.

-------------------------------

Security Considerations :

-- The activation link is sent to the mail id so that only the authorised mail id individual can login and access the service.


