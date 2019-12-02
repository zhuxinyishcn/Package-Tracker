#   PACKAGE TRACKER

##  Instructions to build and run the program

In order to connect to the SQL, create a file `database.info` into `src/main/resources`.   
Type in there three line and change info into the file.  

username: *YourUserName*   
password: *YourPassWord*  
url: jdbc:mysql://*YourURL*  



##  Description

Bohn's Drones is a courier service using small unmanned aerial systems (SUAS)
to deliver packages within the Lincoln and Omaha, Nebraska, areas.  When a
customer needs a package to be delivered, a SUAS is dispatched from a nearby
depot to pick up the package.

Because the SUAS has a limited range, there are depots every ten miles along
I-80 between Seward and the Missouri River.  There are also depots in
Lincoln at the intersection of O Street and 27th Street, at the intersection of
O Street and 84th Street, and at the intersection of 84th Street and Nebraska
Highway 2.  When a SUAS with a package arrives at a depot, the package is
handed off to another SUAS which will carry the package to the next depot or
the destination (if the destination is within range).

A customer (both the sender and the receiver) should be able to observe the
status of a delivery, to include the point of origin and the destination,
when the SUAS was dispatched, when the SUAS picked up the package, when the
package was handed off to another SUAS at each depot visited, and when the
package was delivered.  A customer should also be able to generate a delivery
request, which will cause a SUAS to be dispatched automatically to pick up the
package.

The Bohn's Drones staff should be able to observe where each SUAS is, whether
at a depot, between depots, en route to/from a customer, or at a customer's
location.  The staff should be able to observe which package is aboard which
SUAS.  Just as the customers can, the staff should be able to observe a
package's status.  While dispatching a SUAS to pick up a package is automatic,
the staff should be able to dispatch an empty SUAS from one depot to another.

Current information about the SUAS & package locations & destinations must be
recoverable after a power outage.

The system may be implemented in text-mode or GUI-mode.

##  Rubrics

The assignment is worth **100 points**:

-   **8 points** for providing meeting minutes (1 point per meeting).
-   **15 points** for implementing the functionality as required.
-   **10 points** for following Scrum practices.
-   **16 points** for making good design decisions.
-   **16 points** for using good coding style.
-   **6 points** for class diagram (3 points per sprint)
-   **10 points** for meaningful and well-formatted commit messages.
-   **9 points** for otherwise following good software engineering practices.
-   **10 points** for presentation/demonstration.

The contribution is worth **20 points**:

-   **1 point** for completing sprint 1 peer assessment
-   **1 point** for completing sprint 2 peer assessment
-   **5 points** for equitable contribution based on sprint 1 peer assessments
-   **5 points** for equitable contribution based on sprint 2 peer assessments
-   **8 points** for equitable contribution based on git history
