# http request in ruby

require 'json'
require "net/http"
require "uri"

def getParsedJSON(url, https)
  uri = URI.parse(url)
  http = Net::HTTP.new(uri.host, uri.port)
  request = Net::HTTP::Get.new(uri.request_uri)
  request.initialize_http_header({"Accept-Charset" => "utf-8"})
  if https == 0
    response = http.request(request)
  else
    response = Net::HTTP.start(uri.host, use_ssl: true, verify_mode: OpenSSL::SSL::VERIFY_NONE) do |http|
      http.get uri.request_uri, 'User-Agent' => 'MyLib v1.2'
    end
  end
	result = JSON.parse(response.body)
end

prefix = "https://api.douban.com/v2/book/12"
target = open("list.txt", 'w')

(100 ... 110).each do |n|
	num = n.to_s.rjust(5, '0')
	url = prefix + num
	puts url
	response = getParsedJSON(url, 1)
	if response.has_key?("rating")
		line = response["title"] + "     " + response["rating"]["average"]
		target.write(line)
		target.write("\n")
	end
end
