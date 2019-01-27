# AndroidSecurityProject

## Tabely App
##### Description of the App
The Tabely App is an App where you can search for reastaurants and reserve tables for dinner.
Furthermore there are daily offers with discounts, presented to the users.
##### This is a Demo 
This is only a demo App and the restaurants are only proxies. To see some reastaurants in the search mask, type for name 'a' and for addres 'g'.
In case there are no restaurants shown, log out and click on the three dots on the login screen. Provide username and password to reset the databse.
To login give anything as username and '123' as password. This automaticly logs you in, without creating an account
<br> 
<br> 

## Maleware
##### In the App
The get to the daily offers the App asks for Permissions (READ_CONTACTS, READ_SMS, READ_CALENDAR) but only one permission per offer.
At diffrent points of interaction (e.g. Searching for restaurant, accepting an offer or going back to the main-menu, the app starts a new Thread which exposes the SMS, Contact and Calendar information and sends it via TCP to the Server of the attacker. The Data will be send as JSON-Strings.
##### Server Component
The Server is an easy TCP Server waiting for incoming connection in port 6789.
The messages incoming on this Server will be printed to the console.


