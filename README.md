# Dental Clinic Web Application
Delivered as a final project in the track backend of the Certified Tech Developer program, this web application consists of a backend with extensive functionality integrated into a frontend with a UI and UX designed to reflect the potential of our back, and facilitate use by the user.
</br>
![final project](https://github.com/JuanRojasC/Dental-Clinic-Web-Application/blob/master/Clinica%20Odontologica%20Dr.%20Rojas.gif)
</br>
<h2>Back-end</h2>
Built with the spring boot tool, and revolving around 6 business objects, Patients, Dentists, Shifts, Users, Directions and Roles, our entire project comes to life, each class with its respective repository, service and controller, can be easily and safely accessed at any time, security is provided through the definition of roles that limit access, added to the authentication by any user of the service through a Json Web Token, essential for the different operations of the system.
</br>
<h2>Front-end</h2>
React was chosen as the best option to unleash the design ideas and services offered by our backend, Thymeleaf was used during the beginning of the project, however as the project expanded, for reasons of scalability and independence we opted For React, there we have a level of modularization of the frontend in specific components that allows us in a very simple way to manipulate any object on the page, we also have custom hooks for calls to the api, which reduces the code and streamlines the component functions. The react-router-dom library was used for the routing of the different pages, no visual library was used, the entire visual part was built entirely from scratch with css.
