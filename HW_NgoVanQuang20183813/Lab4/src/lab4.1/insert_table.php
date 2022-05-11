<html>
    <head>
        <title>Insert Results</title>
    </head>
    
    <body>
        <?php
            $server = 'localhost';
            $user   = 'lampdb';
            $pass   = 'Anhemta123';
            $mydb   = 'mydatabase';
            $table_name = 'Products';

            // variables were geted form resquest
            // reference from stackoverflow: https://stackoverflow.com/questions/18023440/php-mysql-insert-into-with-get-method
            if(isset($_GET['item_description']))
            {
                $item_description = $_GET['item_description'];
            } else {
                $item_description = "";
            }
            
            if(isset($_GET['cost']))
            {
                $cost = $_GET['cost'];
            } else {
                $cost = "";
            }

            if(isset($_GET['weight']))
            {
                $weight = $_GET['weight'];
            } else {
                $weight = "";
            }

            if(isset($_GET['number_available']))
            {
                $number_available = $_GET['number_available'];
            } else {
                $number_available = "";
            }

            $link = mysqli_connect($server, $user, $pass, $mydb);
 
            // Check connection
            if($link === false){
                die("ERROR: Could not connect. " . mysqli_connect_error());
            }
 
            // Attempt create table query execution
            $sql = "INSERT INTO $table_name VALUES('0', '$item_description' , '$cost', '$weight', '$number_available')";

            if(mysqli_query($link, $sql)){
                print "The Query is INSERT INTO $table_name VALUES($item_description, $cost, $weight, $number_available)";
                print "Insert into perl_pgm_com was successfull";
            } else{
                die ("Table Create Creation Failed SQLcmd=$sql");
            }
 
            // Close connection
            mysqli_close($link);
        ?>
    </body>
</html>