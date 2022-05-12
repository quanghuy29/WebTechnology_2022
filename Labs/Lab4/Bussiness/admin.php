<html>
    <head><title>Update Product</title></head>
    <body>
        <font size = "10">Category Administration</font>
        <form action = "<?php echo $_SERVER["PHP_SELF"]?>" method = "GET">
        <?php
            $server = '127.0.0.1';
            $user = 'root';
            $pass = '';
            $mydb = 'business_service';
            $table_name = 'Category';
            $name = $_GET["name"];
            $connect = mysqli_connect($server, $user, $pass, $mydb); 
            if (!$connect) {
                die ("Cannot connect to $server using $user"); 
            } else {
                if(array_key_exists("catID", $_GET)){
                    $id = $_GET["catID"];
                    $title = $_GET["title"];
                    $description = $_GET["description"];
                    $SQLInsert = "INSERT INTO $table_name VALUES ('$id', '$title', '$description')";
                    mysqli_select_db($connect, $mydb);
                    mysqli_query($connect, $SQLInsert);           
                }       

                $SQLcmd = "SELECT * FROM $table_name";
                mysqli_select_db($connect, $mydb); 
                if (mysqli_query($connect, $SQLcmd)){
                    $result = mysqli_query($connect, $SQLcmd);
                    print "<table border = 1> "
                            . "<tr>"
                            . "<td align='center' maxlength = '20'>Cat ID</td>"
                            . "<td align='center'>Title</td>"
                            . "<td align='center'>Description</td>"
                            . "</tr>";                      
                    while ($row = mysqli_fetch_array($result)){
                        print "<tr>"
                            . "<td>".$row["CategoryID"]."</td>"
                            . "<td>".$row["Title"]."</td>"
                            . "<td>".$row["Description"]."</td>"               
                            . "</tr>";
                        }               
                            print "<tr>"
                            . "<td><input type='text' size='20' maxlength='50' name='catID'></td>"
                            . "<td><input type='text' size='50' maxlength='50' name='title'></td>"
                            . "<td><input type='text' size='50' maxlength='50' name='description'></td>"              
                            . "</tr>";
                    print "</table>";
                    print "<br><input type ='submit' value='Add Category'>";

                    } else {
                        die ("Table Creation Failed Sqlcmd = $SQLcmd");
                }
            mysqli_close($connect);
            }
        ?>
        </form>
    </body>
</html>


