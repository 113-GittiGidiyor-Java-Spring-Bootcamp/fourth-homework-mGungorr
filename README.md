![homework](https://user-images.githubusercontent.com/45206582/131386439-6727321a-5a50-4c20-9413-ea4013013434.PNG)

### What Is This ?

This project is a school management system has MapstructApi, Logger , DTO layer, exceptions and more...

### What Can It Does ?

- CRUD operations on Student,Courses and Instructors
- Auto Mapstruct between DTO's and Model Classes (Entities)
- Logging in every exception and you can get them from database

### Project Structure

- Config
- DTO
- Exceptions
- Mappers
- Model (Entity)
  - Enumaration
- Repository (DAO)
- Service

### Exceptions
```
- public CourseIsAlreadyExistException(String message) {}
- public InstructorIsAlreadyExistException(String message) {}
- public StudentAgeNotValidException(String message) {}
- public StudentNumberForOneCourseExceededException(String message) {}
```
### Usage

1. Open it and run Main class.
2. When project is running, You can use api adresses on Controllers from web browser, you can use Postman or you can also use Swagger (enabled by default), depends on you :)
3. Feel free to ask me anything :)

