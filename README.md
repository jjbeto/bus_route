# Solution

The solution was entirely developed using Spring Boot. I had some problems to make the "app auto reload in case
data-file changes" feature, but at the end if works pretty well.


### REST layer

Based only on Spring Boot out of the box. I've just created a test to make sure that the 2 possible answers (true/false)
was working as expected.


### Auto Reload on file changes

I never had to build this kind of feature, but at the end it was awesome to make the app reload itself according to some
environment behave. For this I had to make a watcher on the file to verify if something happens to it, in this case I
just shut down the Spring Context (so Hibernate automatically drops all tables from H2) and start it again (and again
Hibernate comes to the rescue, recreating all tables back). During the Spring Context load, the service layer is started
and at the first time that FileDataLoadService is loaded, than all information on data file informed to the system will
be loaded on H2 database.

### Data file load

There is no magic way to load a custom data file, so I decided to develop a simple algorithm to manage this task. The
main point on this part is that this loader can afford big files, considering that it's loading from file line by line
as a stream, avoiding to put the app in some memory trouble, then each line is put in the H2 for further usage and in
the end a simple validation is made over the total lines and the header (total os records informed on the first line).


### Unit test

All functional requirements created and tested before development.


### Possible improvements

Probably the main point to improve is a cache implementation and change for a NoSQL database.
