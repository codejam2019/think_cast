//var http = require('http');

//var app = http.createServer(function(req,res){
//    res.setHeader('Content-Type', 'application/json');
//    res.end(JSON.stringify({ a: 1 }, null, 3));
//});
//app.listen(3000);
//


const axios = require('axios')

axios.post('https://localhost:3000', JSON.stringify([
{"city" : "CITY1", "sensor" : "SENSOR1", "temperature" : "25", "rainfall" : "20", "timestamp" : "2012-04-30T18:25:13"},
{"city" : "CITY1", "sensor" : "SENSOR1", "temperature" : "30", "rainfall" : "12", "timestamp" : "2012-04-30T18:27:43"},
{"city" : "CITY2", "sensor" : "SENSOR1", "temperature" : "35", "rainfall" : "11", "timestamp" : "2012-04-30T18:23:43"},
{"city" : "CITY3", "sensor" : "SENSOR1", "temperature" : "21", "rainfall" : "5", "timestamp" : "2012-04-30T18:27:32"},
{"city" : "CITY4", "sensor" : "SENSOR1", "temperature" : "32", "rainfall" : "16", "timestamp" : "2012-04-30T18:11:43"},
{"city" : "CITY5", "sensor" : "SENSOR1", "temperature" : "34", "rainfall" : "25", "timestamp" : "2012-04-30T18:04:43"},
{"city" : "CITY6", "sensor" : "SENSOR1", "temperature" : "23", "rainfall" : "32", "timestamp" : "2012-04-30T18:22:43"},
{"city" : "CITY7", "sensor" : "SENSOR1", "temperature" : "19", "rainfall" : "45", "timestamp" : "2012-04-30T18:28:43"},
{"city" : "CITY8", "sensor" : "SENSOR1", "temperature" : "12", "rainfall" : "11", "timestamp" : "2012-04-30T18:04:43"},
{"city" : "CITY9", "sensor" : "SENSOR1", "temperature" : "43", "rainfall" : "24", "timestamp" : "2012-04-30T18:08:43"},
{"city" : "CITY10", "sensor" : "SENSOR1", "temperature" : "22", "rainfall" : "20", "timestamp" : "2012-04-30T18:20:43"},
{"city" : "CITY1", "sensor" : "SENSOR2", "temperature" : "14", "rainfall" : "25", "timestamp" : "2012-04-30T18:00:41"},
{"city" : "CITY2", "sensor" : "SENSOR2", "temperature" : "18", "rainfall" : "28", "timestamp" : "2012-04-30T18:06:40"},
]))
.then((res) => {
  console.log(`statusCode: ${res.statusCode}`)
  console.log(res)
})
.catch((error) => {
  console.error(error)
})

