# AndroidSecurityProject


### Tabely App
The Tabely App is an App where you can search for reastaurants and reserve tables for dinner.
Furthermore there are daily offers with discounts, presented to the users.


### Maleware
The get to the daily offers the App asks for Permissions (READ_CONTACTS, READ_SMS, READ_CALENDAR) but only one permission per offer.
At diffrent points of interaction (e.g. Searching for restaurant, accepting an offer or going back to the main-menu, the app starts a new Thread which exposes the SMS, Contact and Calendar information and sends it via TCP to the Server of the attacker. The Data will be send as JSON-Strings.


### Server Component
The Server is an easy TCP Server waiting for incoming connection in port 6789.
The messages incoming on this Server will be printed to the console.
