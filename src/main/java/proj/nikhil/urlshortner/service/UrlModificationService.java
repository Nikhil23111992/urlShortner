package proj.nikhil.urlshortner.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import proj.nikhil.urlshortner.dto.Error;
import proj.nikhil.urlshortner.dto.UrlData;
import proj.nikhil.urlshortner.dto.UrlRequest;
import proj.nikhil.urlshortner.dto.UrlResponse;
import proj.nikhil.urlshortner.repository.SequenceRepository;
import proj.nikhil.urlshortner.repository.UrlCurdRepository;
import proj.nikhil.urlshortner.utility.Base62Utility;

@Service
public class UrlModificationService
{

    private static final Logger logger = LoggerFactory.getLogger(UrlModificationService.class);

    @Autowired
    SequenceRepository seqRepo;

    @Autowired
    UrlCurdRepository urlcurdRepo;

    @Autowired
    Base62Utility base62Utility;
    
    @Value("${urlshortner.shortcode}")
    private String urlname;

    @Transactional
    public UrlResponse createShortUrl(UrlRequest urlRequest)
    {

        String methodName = "createShortUrl-service";
        logger.info(methodName + "Entry : " + urlRequest);
        UrlResponse urlResponse = new UrlResponse();

        if (null == urlRequest.getUrl() || urlRequest.getUrl().isEmpty())
        {

            setErrorMessage(urlResponse, "Input Url missing");
        }
        else
        {
            UrlData urlData;

            urlResponse.setUrl(urlRequest.getUrl());
            urlData = urlcurdRepo.getFindByUrl(urlRequest.getUrl());
            if (null == urlData)
            {
                urlData = new UrlData();
                String base62EncodeStr = base62Utility.encode(seqRepo.getNextValSequence().longValue());
                urlData.setUrl(urlRequest.getUrl());
                urlData.setShortUrl(base62Utility.encode(seqRepo.getNextValSequence().longValue()));
                urlcurdRepo.save(urlData);

            }
            else
            {
                setErrorMessage(urlResponse, "Record alreay present");
            }

            urlResponse.setShortUrl(urlname + urlData.getShortUrl());

        }

        logger.info(methodName + "Exit : " + urlResponse);

        return urlResponse;
    }

    private void setErrorMessage(UrlResponse urlResponse, String message)
    {
        Error error = new Error();
        error.setErrorcode("1");
        error.setErrorDescription(message);
        List<Error> errosList = new ArrayList<>();
        errosList.add(error);
        urlResponse.setErrors(errosList);
    }

    public UrlResponse getShortUrl(String shortUrl)
    {

        String methodName = "getShortUrl-service";
        logger.info(methodName + "Entry : " + shortUrl);
        UrlResponse urlResponse = new UrlResponse();
        String tempSplit[] = shortUrl.split("url/");
        UrlData urlData = null;
        if (tempSplit.length > 0)
        {
            urlData = urlcurdRepo.getFindByShortUrl(tempSplit[1]);
        }
        if (urlData != null)
        {
            urlResponse.setShortUrl(urlname+ tempSplit[1]);
            urlResponse.setUrl(urlData.getUrl());
        }
        else
        {
            Error error = new Error();
            error.setErrorcode("1");
            error.setErrorDescription("No matching short Found");
            List<Error> errosList = new ArrayList<>();
            errosList.add(error);
            urlResponse.setErrors(errosList);

        }

        logger.info(methodName + "Exit : " + urlResponse);
        return urlResponse;
    }

}
