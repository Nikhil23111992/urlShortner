package proj.nikhil.urlshortner.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import proj.nikhil.urlshortner.dto.UrlRequest;
import proj.nikhil.urlshortner.dto.UrlResponse;
import proj.nikhil.urlshortner.service.UrlModificationService;

@RestController
@RequestMapping(value = "/api/url/")
@Api(tags = "Endpoint for getting mini url with an alias", produces = "application/json")
public class UrlCreatorController
{

    @Autowired
    UrlModificationService urlModififcationService;

    private static final Logger logger = LoggerFactory.getLogger(UrlCreatorController.class);

    @PostMapping(value = "save")
    @ResponseBody
    @ApiOperation(value = "Get Shorten url ", notes = "This api returns the short url ")
    @ApiResponses(value = { @ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden") })
    public UrlResponse createShortUrl(@RequestBody UrlRequest urlRequest)
    {
        String methodName = "createShortUrl";
        logger.info(methodName + "Entry : " + urlRequest);

        UrlResponse urlResponse = urlModififcationService.createShortUrl(urlRequest);

        logger.info(methodName + "Exit : " + urlResponse);
        return urlResponse;
    }

    @GetMapping(value = "getShortUrl")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Request encountered an unexpected error"),
            @ApiResponse(code = 400, message = "Request contained data that was invalid"),
            @ApiResponse(code = 200, message = "Item details with availability fetched successfully"),
            @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden") })
    public UrlResponse getShortUrl(@RequestParam("shorturl") String shorturl)
    {
        String methodName = "getShortUrl";
        logger.info(methodName + "Entry : " + shorturl);

        UrlResponse urlResponse = urlModififcationService.getShortUrl(shorturl);

        logger.info(methodName + "Exit : " + urlResponse);
        return urlResponse;
    }

}
