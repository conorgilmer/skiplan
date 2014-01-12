<?php
/*
* Generate JSON
* for coffee shop (corona) example
* write to stream using callback function
* e.g http://localhost/genjson.php?callback=listposts 
*
* TODO- Array could be populated from Database
*
* By Conor Gilmer
*/


$data = array("data" => array(
  "skinews" => array(
	"news" => array (
			array ( "title" => "Open Pistes",
				"country" => "USA",
				"desc" => "Opening of North Easts most popular ski runs",
				"author" => "BBC Ski Report",
				"date" => "12122013"
				),  

			array ( "title" => "Italian World Cup Venue",
				"country" => "Italy",
				"desc" => "All Ski Runs Open",
				"author" => "Ski Club",
				"date" => "02122013"
				)  
			)
		,
	"forecast" => array (
			array ( "title" => "More Snow Expected",
				"country" => "Germany",
				"desc" => "10-12cm expected overnight",
				"author" => "RTE Weather",
				"date" => "01012014"
				),  

			array ( "title" => "Icy Conditions",
				"country" => "France",
				"desc" => "Low temperatures will result in icy condidtions on most ski areas in alpine france",
				"author" => "BBC Ski Weather",
				"date" => "03012014"
				)  
			)
		)
	)
	);

//encode data to json
$output =  json_encode($data);

//format for callback function
if(array_key_exists('callback', $_GET)){

    header('Content-Type: text/javascript; charset=utf8');
    header('Access-Control-Allow-Origin: http://www.example.com/');
    header('Access-Control-Max-Age: 3628800');
    header('Access-Control-Allow-Methods: GET, POST, PUT, DELETE');

    $callback = $_GET['callback'];
    echo $callback.'('.$output.');';

}else{

// Output to console
header('Content-type: application/json; charset=utf8');
echo $output;
}
?>
