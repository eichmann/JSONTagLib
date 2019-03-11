package org.cd2h.JSONTagLib.GraphQL;

import org.apache.log4j.Logger;
import org.cd2h.JSONTagLib.util.PropertyLoader;

public class GitHubAPI extends GraphQLAPI{
    static Logger logger = Logger.getLogger(GitHubAPI.class);

    static String me = "viewer { login,name,id }";
    static String repo_list = "viewer {"
	    	+ "	repositories(first:100) {"
	    	+ "		nodes {"
	    	+ "			name,"
	    	+ "			url,"
	    	+ "			id,"
	    	+ "			updatedAt,"
	    	+ "			milestones(first:100) {"
	    	+ "				nodes {"
	    	+ "					id,"
	    	+ "					title,"
	    	+ "					description,"
	    	+ "					dueOn,"
	    	+ "					creator{login}"
	    	+ "				}"
	    	+ "			},"
	    	+ "			repositoryTopics(first:100) {"
	    	+ "				nodes {"
	    	+ "					topic{name}"
	    	+ "				}"
	    	+ "			}"
//	    	+ "			collaborators(first:100) {"
//	    	+ "				nodes {"
//	    	+ "					login,name"
//	    	+ "				}"
//	    	+ "			}"
	    	+ "		}"
	    	+ "	}"
	    	+ "}";
    static String repo = "repository(owner:eichmann, name:SPARQLTagLib) {"
    	+ "	id,"
    	+ "	url"
    	+ "			repositoryTopics(first:100) {"
    	+ "				nodes {"
    	+ "					topic{name}"
    	+ "				}"
    	+ "			}"
    	+ "}";
    static String repoDetail = "repository(owner: data2health, name: \"$repo\") {"
    	+ "    name"
    	+ "    description"
    	+ "    url"
    	+ "    pushedAt"
    	+ "    milestones(first: 100, states:OPEN, orderBy:{field:DUE_DATE,direction:ASC}) {"
    	+ "      totalCount"
    	+ "      nodes {"
    	+ "        id"
    	+ "        title"
    	+ "        description"
    	+ "        dueOn"
    	+ "        closed"
    	+ "        creator{login}"
    	+ "        state"
    	+ "      }"
    	+ "    }"
    	+ "    issues(first: 100, states:OPEN, orderBy:{field:CREATED_AT,direction:ASC}) {"
    	+ "      totalCount"
    	+ "      nodes {"
    	+ "        number"
    	+ "        title"
    	+ "        body"
    	+ "        state"
    	+ "        author{login}"
    	+ "        milestone {"
    	+ "          title"
    	+ "        }"
    	+ "        assignees(first:100) {"
    	+ "           nodes {"
    	+ "              name"
    	+ "              login"
    	+ "              avatarUrl"
    	+ "           }"
    	+ "        }"
    	+ "      }"
    	+ "    }"
    	+ "  }";
    static String repo_mutate = " updateTopics ("
    				+ "	input: {"
    				+ "		repositoryId: \"MDEwOlJlcG9zaXRvcnkxMzQzMTU4NDk=\","
    				+ "		topicNames:[\"data2health\", \"pea\"]"
    				+ "	}"
    				+ ") {"
    				+ "	clientMutationId"
    				+ "}";
    static String data2health_org = "organization(login:data2health) {"
    	+ "	id,"
    	+ "	login,"
    	+ "	repositories(first:100, orderBy:{field:NAME, direction: ASC}) {"
    	+ "		nodes {"
    	+ "			name,"
    	+ "			description,"
    	+ "			url,"
    	+ "			milestones(first:100) {"
    	+ "				nodes {"
    	+ "					id,"
    	+ "					title,"
    	+ "					description,"
    	+ "					dueOn,"
    	+ "					creator{login}"
    	+ "				}"
    	+ "			},"
    	+ "			repositoryTopics(first:100) {"
    	+ "				nodes {"
    	+ "					topic{name}"
    	+ "				}"
    	+ "			}"
//    	+ "			collaborators(first:100) {"
//    	+ "				nodes {"
//    	+ "					login,name"
//    	+ "				}"
//    	+ "			}"
    	+ "		}"
    	+ "	}"
    	+ "	members(first:100) {"
    	+ "		nodes {"
    	+ "			id,name,bio,email,login,avatarUrl"
    	+ "		}"
    	+ "	}"
    	+ "}";
    static String memberList = "organization(login:data2health) {"
	    	+ "	members(first:100) {"
	    	+ "		nodes {"
	    	+ "			id,name,bio,email,login,avatarUrl"
	    	+ "		}"
	    	+ "	}"
	    	+ "}";
    static String repoByTopic = "search(query: \"topic:data2health\", type: REPOSITORY, first:100) {"
    				+ "	repositoryCount"
    				+ "	edges {"
    				+ "		node {"
    				+ "		... on Repository {"
    				+ "			owner {"
    				+ "				id"
    				+ "				login"
    				+ "			}"
    				+ "			name"
    				+ "			description"
    				+ "			stargazers {"
    				+ "				totalCount"
    				+ "			}"
    				+ "			forks {"
    				+ "				totalCount"
    				+ "			}"
    				+ "			updatedAt"
    				+ "			}"
    				+ "		}"
    				+ "	}"
    				+ "}";
    static String projectDashboard = "organization(login: data2health) {"
    				+ "    repositories(first: 100, orderBy:{field:PUSHED_AT,direction:DESC}) {"
    				+ "      nodes {"
    				+ "        name"
    				+ "        description"
    				+ "        url"
    				+ "        pushedAt"
    				+ "        milestones(first: 100) {"
    				+ "          totalCount"
    				+ "          nodes {"
    				+ "            dueOn"
    				+ "            closed"
    				+ "          }"
    				+ "        }"
    				+ "        issues(first: 100) {"
    				+ "          totalCount"
    				+ "          nodes {"
    				+ "            closed"
    				+ "          }"
    				+ "        }"
    				+ "      }"
    				+ "    }"
    				+ "  }";
    
    static String projectDashboardsingle = "search(query: \"topic:cd2hpm\", type: REPOSITORY, first: 100) {"
    		+"		repositoryCount"
    		+"	    edges {"
    		+"	      node {"
    		+"	        ... on Repository{"
    		+"	          name"
    		+"	          description"
    		+"	          url"
    		+"			  updatedAt"
    		+" 			  milestones(first:100){"
    		+"				totalCount"
        	+"				edges {"
          	+"					mil: node {"
            +"						title"
            +"						description"
            +"    					dueOn"
          	+"					}"
       		+" 				}"
        	+" 			  }" 
    		+"	          open_milestones: milestones(states: OPEN) {"
    		+"	            totalCount"
    		+"	          }"
    		+"	          closed_milestones: milestones(states: CLOSED) {"
    		+"	            totalCount"
    		+"	          }"
    		+"	          total_milestones: milestones{"
    		+"	            totalCount"
    		+"	          }"
    		+"	          open_issues : issues(states: OPEN) {"
    		+"	            totalCount"
    		+"	          }"
    		+"	          closed_issues : issues(states: CLOSED) {"
    		+"	            totalCount"
    		+"	          }"
    		+"	          total_issues : issues{"
    		+"	            totalCount"
    		+"	          }"
    		+"	        }"
    		+"	      }"
    		+"	    }"
    		+"	}";
    
    static String roadmapList = "search(query: \"topic:cd2hpm\", type: REPOSITORY, first: 100) {"
    				+ "    repositoryCount"
    				+ "    nodes {"
    				+ "      ... on Repository {"
    				+ "        name"
    				+ "        description"
    				+ "        url"
    				+ "        updatedAt"
    				+ "      }"
    				+ "    }"
    				+ "  }";
    
    public GitHubAPI() {
	super("GitHub");
	props = PropertyLoader.loadProperties("github");
	
	registerStatement("me", me);
	registerStatement("repo_list", repo_list);
	registerStatement("repo", repo);
	registerStatement("repoDetail", repoDetail);
	registerStatement("repo_mutate", repo_mutate);
	registerStatement("data2health_org", data2health_org);
	registerStatement("data2health_tagged_repos", repoByTopic);
	registerStatement("projectDashboard", projectDashboard);
	registerStatement("projectDashboardsingle", projectDashboardsingle);
	registerStatement("roadmapList", roadmapList);
	registerStatement("memberList", memberList);
    }

}
