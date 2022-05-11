<html>
    <head>
        <title>Create Table</title>
    </head>
    
    <body>
        <?php
            $server = 'localhost';
            $user   = 'lampdb';
            $pass   = 'Anhemta123';
            $mydb   = 'mydatabase';
            $table_name = 'Products';
            
            $link = mysqli_connect($server, $user, $pass, $mydb);
 
            // Check connection
            if($link === false){
                die("ERROR: Could not connect. " . mysqli_connect_error());
            }
 
            // Attempt create table query execution
            $sql = "CREATE TABLE $table_name(
                        ProductID INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        Product_desc VARCHAR(50),
                        Cost INT,
                        Weight INT,
                        Numb INT)";

            if(mysqli_query($link, $sql)){
                print '<font size="4" color="blue" >Created Table';
                print "<i>$table_name</i> in database<i>$mydb</i><br></font>";
                print "<br>SQLcmd=$sql";
            } else{
                die ("Table Create Creation Failed SQLcmd=$sql");
            }
 
            // Close connection
            mysqli_close($link);
        ?>
    </body>
</html>