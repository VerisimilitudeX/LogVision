# Apache HTTP Server 2.4 Log Analytics
> To effectively manage a web server, it is essential to get feedback on the server's activity and performance, as well as any problems that may arise. This software analyzes Apache HTTP Server log entries in order to configure its logging capabilities.

## Mechanisms
Please refer to the following documention for more information on the Apache HTTP Server Version 2.4 logging format: https://httpd.apache.org/docs/2.4/logs.html

## Features
* Log file parsing
  * IP address, access time, request, status code, and response size extraction
* IP address analytics
  * User location, ISP, and ASN external lookup
* User analytics per IP address
  * Visits, unique visits, and average visit duration
* Error code analytics
  * Filtered by status code
* Time and date analytics
  * Hourly, daily, and monthly visit analytics with error code breakdown
* Data transfer analytics
  * Bytes transferred, average transfer rate, and transfer rate distribution
* Highest traffic IP address, hits, bytes, and date
  * User location, ISP, and ASN external lookup
* CSV output
  * Can be used to import into a spreadsheet