<html>
    <head>
        <title>Table Output</title>
        <style>
            table, th, td {
                border:1px solid black;
            }
            .input_form {
                padding: 5px;
                margin-right: 5px;
                margin-bottom: 15px;
                margin-top: 15px;
                width: 232px;
            }
        </style>
    </head>
    
    <body>
        <?php
            $server = 'localhost';
            $user   = 'lampdb';
            $pass   = 'Anhemta123';
            $mydb   = 'business_service';
            $table_name = 'Categories';

            // variables were geted form resquest
            // reference from stackoverflow: https://stackoverflow.com/questions/18023440/php-mysql-insert-into-with-get-method
            

            $link = mysqli_connect($server, $user, $pass, $mydb);
 
            // Check connection
            if($link === false){
                die("ERROR: Could not connect. " . mysqli_connect_error());
            }
 
            // Attempt create table query execution
            $sql = "SELECT * from $table_name";
            
            $getAllCategories = mysqli_query($link, $sql);
            if($getAllCategories){
                console.log("The Query is SELECT * FORM $table_name is successfull");
                
            } else{
                die ("Query Failed SQLcmd=$sql");
            }
 
            // Close connection
            mysqli_close($link);
        ?>

        <h2 style="color: blue;">Category Adminstration</h2> 
            <table style="width:50%">
                <tr>
                    <th>CatID</th>
                    <th>Title</th>
                    <th>Description</th>
                </tr>
        <?php foreach($getAllCategories as $category): ?>
                <tr>
                    <td><span><?php echo $category['CategoryID'] ?></span></td>
                    <td><span><?php echo $category['Title'] ?></span></td>
                    <td><span><?php echo $category['Description'] ?></span></td>
                </tr>
        <?php endforeach; ?>
            </table>
        
        <div>
            <form action="/bizness/insert_category.php" method="get" target="_blank">
                <input class="input_form" type="text" id="category_id" name="category_id">
                <input class="input_form" type="text" id="category_title" name="category_title">
                <input class="input_form" type="text" id="category_description" name="category_description">
                <br>
                <input type="submit" id="submit_btn" value="Add Category">
            </form>
        </div>
    </body>
</html>