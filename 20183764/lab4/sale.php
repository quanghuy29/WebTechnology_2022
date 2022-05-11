<html>
    <head><title>Update Product</title></head>
    <body>
        <?php
            $server = '127.0.0.1';
            $user = 'root';
            $pass = '';
            $mydb = 'lab4';
            $table_name = 'Products';
            $name = $_GET["name"];
            $connect = mysqli_connect($server, $user, $pass, $mydb); 
            if (!$connect) {
                die ("Cannot connect to $server using $user"); 
            } else {
                $SQLupdate = "UPDATE $table_name SET Numb = Numb-1 WHERE (Product_desc = '$name')";
                if (mysqli_query($connect, $SQLupdate)){
                   print '<font size  = "10" color="blue">Update Results For Table Products</font>';
                   print "<br>SQLcmd = $SQLupdate<br>";
                } else {
                    die ("Table Creation Failed Sqlcmd = $SQLupdate");
                }
                $SQLcmd = "SELECT * FROM $table_name";
                mysqli_select_db($connect, $mydb); 
                if (mysqli_query($connect, $SQLcmd)){
                    $result = mysqli_query($connect, $SQLcmd);                                               
                    print "<table border = 1> "
                            . "<tr>"
                            . "<td>Num</td>"
                            . "<td>Product</td>"
                            . "<td>Cost</td>"
                            . "<td>Weight</td>"
                            . "<td>Count</td>"
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

