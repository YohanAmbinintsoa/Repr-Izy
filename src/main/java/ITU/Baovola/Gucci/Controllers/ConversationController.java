package ITU.Baovola.Gucci.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ITU.Baovola.Gucci.DTO.ResponseData;
import ITU.Baovola.Gucci.Models.Conversation;
import ITU.Baovola.Gucci.Models.Message;
import ITU.Baovola.Gucci.Models.User;
import ITU.Baovola.Gucci.Repositories.ConversationRepository;
import ITU.Baovola.Gucci.Repositories.MessageRepository;
import ITU.Baovola.Gucci.Security.Authority;
import ITU.Baovola.Gucci.Security.MyContext;
import ITU.Baovola.Gucci.Security.Role;
import ITU.Baovola.Gucci.Services.ChatService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/v1/Conversations")
public class ConversationController extends BaseController{
    @Autowired
    ConversationRepository conRepo;
    @Autowired
    ChatService chatService;
    @Autowired
    MessageRepository messageRepository;
    
    @GetMapping
    @Authority(role = Role.USER)
    public ResponseData getAllConversations() {
        ResponseData data=new ResponseData();
        try {
            data.addData(chatService.getAllConversation(MyContext.getUser()));
        } catch (Exception e) {
           e.printStackTrace();
           data.setError(e.getMessage());
        }
        return data;
    }

    @PostMapping
    @Authority(role = Role.USER)
    public ResponseData newConversation(HttpServletRequest req) {
        ResponseData data=new ResponseData();
        try {
           List<User> users=new ArrayList<>();
           User current= MyContext.getUser();
           User other=new User();
           other.setId(req.getParameter("iduser"));
           other=(User)MyContext.getRequester().select(null, other).get(0);
           users.add(other);
           users.add(current);
           Conversation conv=new Conversation();
           conv.setUtilisateurs(users);
           data.addData(conRepo.save(conv));
        } catch (Exception e) {
           e.printStackTrace();
           data.setError(e.getMessage());
        }
        return data;
    }
    
    @PostMapping("/newMessage")
    @Authority(role = Role.USER)
    public ResponseData newMessage(HttpServletRequest req) {
        ResponseData data=new ResponseData();
        try {
            Message msg=new Message();
            msg.setIdConversation(req.getParameter("idconversation"));
            msg.setSender(MyContext.getUser());
            User other=new User();
            other.setId(req.getParameter("iduser"));
            other=(User)MyContext.getRequester().select(null, other).get(0);
            msg.setDate(new Date(System.currentTimeMillis()));
            msg.setReceiver(other);
            msg.setMessage(req.getParameter("message"));
            data.addData(this.messageRepository.save(msg));
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }

    @GetMapping("/conversation/{iduser}")
    @Authority(role = Role.USER)
    public ResponseData getMethodName(@PathVariable("iduser") String id) {
        ResponseData data=new ResponseData();
        try {
            User user2=new User();
            user2.setId(id);
            List<User> users=new ArrayList<>();
            users.add(MyContext.getUser());
            users.add((User)this.requester.select(null,user2).get(0));
            Conversation conv=chatService.getConversationOf(users);
            if (conv==null) {
                System.out.println("ATO ELAH");
                conv=new Conversation();
                conv.setUtilisateurs(users);
            }
            Conversation convUser=conv;
            data.addData(convUser);
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }
    
}
