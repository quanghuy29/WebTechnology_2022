<html>
    <head>
        <title>Search Result</title>
    </head>
    <body>
        <?php
            $server = '127.0.0.1';
            $user = 'root';
            $pass = '';
            $mydb = 'lab4';
            $table_name = 'Products';
            $item_desc = $_GET["item_desc"];
            $connect = mysqli_connect($server, $user, $pass, $mydb); 
            if (!$connect) {
                die ("Cannot connect to $server using $user"); 
            } else {
                $SQLcmd = "SELECT * FROM $table_name WHERE (Product_desc = '$item_desc')";
            mysqli_select_db($connect, $mydb); 
            if (mysqli_query($connect, $SQLcmd)){
                $result = mysqli_query($connect, $SQLcmd);
                print '<font size  = "4" color="blue" >Products Data ';
                //print "<i>$table_name</i> in database <i>$mydb</i><br></font>";
                print "<br>The Query is $SQLcmd<br>";
                print "<table border = 1> "
                        . "<tr>"
                        . "<td><b>Num</b></td>"
                        . "<td><b>Product</b></td>"
                        . "<td><b>Cost</b></td>"
                        . "<td><b>Weight</b></td>"
                        . "<td><b>Count</b></td>"
                        . "</tr>";                      
                while ($row = mysqli_fetch_array($result)){
                    print "<tr>"
                        . "<td>".$row["ProductID"]."</td>"
                        . "<td>".$row["Product_desc"]."</td>"
                        . "<td>".$row["Cost"]."</td>"
                        . "<td>".$row["Weight"]."</td>"
                        . "<td>".$row["Numb"]."</td>"
                        . "</tr>";
                }
                print "</table>";
            } else {
                die ("Table Creation Failed Sqlcmd = $SQLcmd");
            }
            mysqli_close($connect);
            }
        ?>
    </body>
</html>