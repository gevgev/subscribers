# subscribers
Demo for Docker/Google Container engine multi layer app deployment

simple REST services, with mySQL backend, JSP Web front, with Docker/Kubernetes Replication controller (Google Container Engine)

Folders content description:

- common:       class library, shared accross web services (services) and web site projects (subscribers) - Subscriber class
- deployment:   Docker files, Kubernetes files, etc.
- services:     Spring Boot REST web services for Subscriber entity
- sql:          SQL DDL script for Subscribers data base 
- subscribers:  Dynamic Web site (JSP) for a simple Web UI app to demo the webservices API's: 
      Get all         (grid page)
      Get one by Id   (for update page)
      Put/Update      (for update page)
      Post/Create     (for create page)
      Delete          (for delete button)
