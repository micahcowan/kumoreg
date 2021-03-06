# KumoReg

Convention Registration Management

- At-Convention and pre-registered attendee check in
- Badge printing                                              
- Reporting                                                      
- Roles and permission management                                
- Allow only certain users to search for / edit / check in       
  attendees / set prices manually                                

[![Home](docs/screenshots/home_t.png)](docs/screenshots/home.png) 
[![Detail](docs/screenshots/detail_t.png)](docs/screenshots/detail.png) 
[![Report](docs/screenshots/report_t.png)](docs/screenshots/report.png)
   
# Requirements
- Server:
  - Java 8
  - MySQL
  - CUPS
- Clients:
  - Web browser
  - IP Addresses that don't change (either statically mapped or assigned via DHCP)
  - Works best at 1920x1080 resolution

# Table of Contents
- [Installation](docs/installation.md)
- [Change Log](CHANGES.md)
- [Security](docs/security/introduction.md)
    - [Rights](docs/security/rights.md)
    - [Roles](docs/security/roles.md)
- [Data Import Format](docs/PreRegDataImportFormat.md)
- [Development Notes](docs/development.md)