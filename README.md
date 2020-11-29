# Banshee

<!-- TABLE OF CONTENTS -->
## Table of Contents

* [About the Project](#about-the-project)
  * [Built With](#built-with)
* [Tools](#tools)
* [Installation](#installation)
* [GitHub Work Flow](#github-work-flow)
* [Usage](#usage)
* [License](#license)
* [Contact](#contact)

## About The Project
Banshee is in progress... Development is supported on the following platforms:
* macOS
* Debian Linux Distributions

### Built With
Banshee is built using Java, Maven, Swift
* [Java](https://www.java.com/en/)
* [Maven](https://maven.apache.org/)
* [Swift](https://swift.org/)
* [Springboot](https://spring.io/projects/spring-boot)

## Tools
* [Discord](https://discord.gg/YEz33PFm)
* [ZenHub](https://app.zenhub.com/workspaces/devlavertubanshee-5fa9919c85fdee000efdb4c0/board?repos=310688472)
* [LucidChart](https://lucid.app/invitations/accept/f3095299-7a80-47d4-8338-031f6f34854b)
* [Intellij](https://www.jetbrains.com/idea/)
    * [SonarLint](https://www.sonarlint.org/intellij)
* [PostgreSQL](https://www.postgresql.org/download/)

## Installation
1. In Intellij File > New Project > From Version Control
2. Choose HTTPS and paste in the repository link
3. Leave defaults and continue through
4. Refresh Maven imports and run Maven install from the Maven tab
5. [Configure Database](https://github.com/elavertu9/Banshee/blob/main/team/database_usage.md)
6. [API Usage](https://github.com/elavertu9/Banshee/blob/main/team/api_usage.md)

## GitHub Work Flow
1. Clone repository using HTTPS
```sh
git clone https://github.com/elavertu9/Banshee.git
```
2. Navigate into the root of the Banshee directory
3. Branch the code base
```sh
git branch <branch-name>
```
4. Checkout newly created branch
```sh
git checkout <branch-name>
```
5. Satisfied with changes? Need to add changes, commit, and push
```sh
git add <file-name(s)>
```
```sh
git commit -m"detailed commit message"
```
```sh
git push
```
6. To place your changes into master, navigate to the repository on GitHub and create a pull request out of your recently pushed changes. Wait for a reviewer to merge the code.
7. If your master branch is out of date, you can run
```sh
git pull upstream master
```
8. From your branch
```sh
git merge master
```

## Usage
After installation, inside Main.java...
1. Comment out commandParserTesting() and uncomment SpringApplication.run(Main.class, args) for localhost:8080 API
2. Comment out SpringApplication.run(Main.class, args) and uncomment commandParserTesting() for local terminal testing

```java 
public static void main(String[] args) {
        //commandParserTesting();
        SpringApplication.run(Main.class, args);
}
```
### Command Parser
* help
* flip <row,col>
* capture <from.row,from.col> <to.row,to.col>
* travel <from.row,from.col> <to.row,to.col>
* allflip

## License
No license yet

## Contact
Website - [www.lavertu.dev](https://www.lavertu.dev)

Project Link: [Banshee](https://github.com/elavertu9/Banshee)
