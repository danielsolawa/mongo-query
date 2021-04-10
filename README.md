# Quick start
- Clone the project using "git clone" command
- Use gradle task "shadowJar"
- Open your project
- Create libs module in root folder and copy the specification jar 
- Add the following dependency to your build.gradle  file
>dependencies {
>     implementation files('libs/something_local.jar')
> }
>
- Add @EnableMongoRepositories to your configuration class and set base repository class to MSpecificationRepositoryImpl
>@EnableMongoRepositories(
         repositoryBaseClass = MSpecificationRepositoryImpl.class)
- Create a repository for desired type and extend MSpecificationRepository