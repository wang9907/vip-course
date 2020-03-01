package com.enjoy;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;


//指定目标方法
@Mojo(name="log")
public class MyEnjoyPlugin  extends AbstractMojo {
    @Parameter( property = "log.name", defaultValue = "Peter" )
    private Object name;//参数

    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info( "hello " +name+ ",deploy to your local repository" );
    }
}
