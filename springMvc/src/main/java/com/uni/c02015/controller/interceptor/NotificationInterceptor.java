package com.uni.c02015.controller.interceptor;

import com.uni.c02015.domain.Message;
import com.uni.c02015.domain.User;
import com.uni.c02015.domain.buddy.Request;
import com.uni.c02015.persistence.repository.MessageRepository;
import com.uni.c02015.persistence.repository.UserRepository;
import com.uni.c02015.persistence.repository.buddy.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class NotificationInterceptor extends HandlerInterceptorAdapter {

  @Autowired
  private MessageRepository messageRepository;
  @Autowired
  private RequestRepository requestRepository;
  @Autowired
  private UserRepository userRepository;

  @Override
  public void postHandle(HttpServletRequest request,
                         HttpServletResponse response,
                         Object handler,
                         ModelAndView modelAndView) throws Exception {

    if (request.getRemoteUser() == null) {

      return;
    }

    User user = userRepository.findByLogin(request.getRemoteUser());

    // Get the unread message count
    List<Message> messages = messageRepository.findByReceiverAndIsRead(user, false);
    request.setAttribute("messageCount", messages.size());

    // Get the amount of unprocessed buddy requests
    List<Request> requests = requestRepository.findByReceiverAndConfirmed(user, false);
    request.setAttribute("unprocessedBuddyCount", requests.size());

    super.postHandle(request, response, handler, modelAndView);
  }
}