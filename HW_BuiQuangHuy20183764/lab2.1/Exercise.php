<<html>
    <head>
        <title>Receiving Input</title>
    </head>
    <body>
        <font size=5> Thank you: Got Your Input.</font>
        <?php
            $name = $_POST["name"];
            $university = $_POST["university"];
            $class = $_POST["class"];
            $love = $_POST["love"];
            $football = $_POST["football"];
            $badminton = $_POST["badminton"];
            $swimming = $_POST["swimming"];
            $volleyball = $_POST["volleyball"];
            $basketball = $_POST["basketball"];
            $Boxing = $_POST["Boxing"];
            print ("<br>Your beautiful name is $name");
            print ("<br>You study at $university and your class is $class");
            print ("<br>Your love university level: $love");
            print ("<br>About your favourite sports: ");
            print ("<br>Football: $football");
            print ("<br>Badminton: $badminton");
            print ("<br>Swimming: $swimming");
            print ("<br>Volleyball: $volleyball");
            print ("<br>Basketball: $basketball");
            print ("<br>Boxing: $Boxing");
        ?>
    </body>
</html>
