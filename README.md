# Exercice 4 (From final project)
This code includes the automation of the next text cases:

**TC_REQ-001_001.**
_User uses the search bar, in the right, to search a term related with the name of an article._

**TC_REQ-003_001.**
_The user navigates in the shop pages looking the products 9 by 9._

**TC_REQ-005_001.**
_Check behavior when a registered user views a product and after looks for recently viewed products._

**TC_REQ007_001.**
_Check behavior when all fields are correctly entered._

**TC_REQ-007_002.**
_Check behavior when name is empty._

**TC_REQ-007_003.**
_Check behavior when email is in wrong format._

**TC_REQ-008_001.**
_Validate that products are added to the cart after click on add to cart button._

**TC_REQ-009_001.**
_Validate that the cart icon reflects when user adds a product to the cart._

**TC_REQ-010_001.**
_Validate when user removes a product from the cart._

**TC_REQ-011_001.**
_Validate a registered user is able to checkout by filling the fields with the proper values._

# How to set up the proyect
## Download the proyect
you can download the proyect by copying this repository using the terminal.

1.On GitHub.com, navigate to the main page of the repository.

2.Above the list of files, click Code.

<img width="536" alt="image" src="https://user-images.githubusercontent.com/101494543/158984785-623a8ce5-845f-4063-86ec-7471d10bdc50.png">

3.To clone the repository using HTTPS, under "Clone with HTTPS", click on the clipboard icon.

<img width="543" alt="image" src="https://user-images.githubusercontent.com/101494543/158984975-a572930d-01a7-426e-bebe-ba82812b6ded.png">

4.Open Terminal and change the current working directory to the location where you want the cloned directory.

<img width="543" alt="image" src="https://user-images.githubusercontent.com/101494543/158985184-9043cd39-9a5c-496f-835a-9ff5c65b90c5.png">

5.Type git clone, and then paste the URL you copied earlier.

>$ git clone https://github.com/IradVS/EXERCISE4

<img width="543" alt="image" src="https://user-images.githubusercontent.com/101494543/158985673-9c28ba0d-b702-47fa-88bb-0d2775f0293a.png">

6.Press Enter to create your local clone.

<img width="576" alt="image" src="https://user-images.githubusercontent.com/101494543/158986371-76240ebb-b915-4304-93b2-cbfe29f2f075.png">

7. thats all. also you can just download the zip code file, but is the not cool way.
## Executing Project on maven console

this is one way to open the proyect if you dont have maven you can install it using brew.

>$ brew install maven

on the directory were you clone the git repository open a terminal and type:

>$ mvn clean test

<img width="576" alt="Captura de Pantalla 2022-03-18 a la(s) 3 44 14" src="https://user-images.githubusercontent.com/101494543/158987084-a995652b-84de-4249-a84c-52f022799109.png">

the suit test called e-commerse will start to be executed.

<img width="576" alt="Captura de Pantalla 2022-03-18 a la(s) 3 48 48" src="https://user-images.githubusercontent.com/101494543/158987307-35c527d7-a33c-4490-99e6-49aa691dbbeb.png">

## Import project on Eclipse
you need to have selenium and testNG installed, in order to confirm if you have it you can go to:

> Eclipse >> About Eclipse >> Instalation details

<img width="740" alt="image" src="https://user-images.githubusercontent.com/101494543/158987655-9e0c77b7-eedb-4114-bf5c-063c098e499c.png">

and look if you have the plugins installed (if you dont, you can use eclipse market place to install it)
in eclipse go to:
> file >> import >> Existing maven project.
<img width="740" alt="image" src="https://user-images.githubusercontent.com/101494543/158988247-2230ff27-20a4-4ded-85eb-2a2202946276.png">
> click on next >> click on browse >> browse the directory and click on open.
<img width="817" alt="image" src="https://user-images.githubusercontent.com/101494543/158988434-f7df4690-b3ee-44af-b95f-913a4521726b.png">
if everything its fine you should be able to click finish and you are ready to go.
<img width="657" alt="image" src="https://user-images.githubusercontent.com/101494543/158988622-7838f69a-5a2f-4153-bd71-9891c4c62366.png">
## Executing suit in Eclipse.
on the root of the proyect open the file testng.xml then perform a right click and go to Run and click As TestNG suit.
<img width="1440" alt="image" src="https://user-images.githubusercontent.com/101494543/158988941-d0ad38bb-7705-428a-ad0c-6a81a28fafeb.png">


