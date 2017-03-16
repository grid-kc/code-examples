/* This script will reload a single job configuration from disk without taking
 * Jenkins down or putting running builds in danger */
import java.io.InputStream;
import java.io.FileInputStream
import java.io.File;
import javax.xml.transform.stream.StreamSource

def h = hudson.model.Hudson.instance

def job = h.getItem('job-name')

def configFile = job.getConfigFile().getFile()

println configFile.absolutePath

InputStream is = new FileInputStream(configFile);
job.updateByXml(new StreamSource(is));
job.save();
